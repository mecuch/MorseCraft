import wave
import numpy as np

class MorseWAVDecoderLetter:
    def __init__(self, path: str, threshold: float = 0.20, min_gap_ms: float = 10.0):
        """
        threshold: próg amplitudy (0–1)
        min_gap_ms: minimalny odstęp między początkami pików (scalanie bardzo bliskich impulsów)
        """
        self.path = path
        self.threshold = threshold
        self.min_gap_ms = min_gap_ms
        self.morse_to_letter = {
            "._": "A", "_...": "B", "_._.": "C", "_..": "D", ".": "E",
            ".._.": "F", "__.": "G", "....": "H", "..": "I", ".___": "J",
            "_._": "K", "._..": "L", "__": "M", "_.": "N", "___": "O",
            ".__.": "P", "__._": "Q", "._.": "R", "...": "S", "_": "T",
            ".._": "U", "..._": "V", ".__": "W", "_.._": "X", "_.__": "Y",
            "__..": "Z"
        }

    def analyze(self):
        """
        Zwraca listę długości każdego wykrytego piku w milisekundach.
        Długość listy = liczba wykrytych pików.
        """
        with wave.open(self.path, "rb") as wf:
            if wf.getnchannels() != 1 or wf.getsampwidth() != 2:
                raise ValueError("plik musi być mono, 16-bit PCM")
            rate = wf.getframerate()
            raw = wf.readframes(wf.getnframes())

        x = np.frombuffer(raw, dtype=np.int16).astype(np.float32) / 32768.0
        absx = np.abs(x)

        # progowanie amplitudy
        mask = absx > self.threshold
        d = np.diff(mask.astype(np.int8))

        # starty/końce segmentów powyżej progu
        starts = np.where(d == 1)[0] + 1
        ends = np.where(d == -1)[0] + 1

        if mask[0]:
            starts = np.r_[0, starts]
        if mask[-1]:
            ends = np.r_[ends, len(mask)]
        if len(starts) == 0 or len(ends) == 0:
            return None

        # scalanie zbyt bliskich impulsów
        min_gap_samp = int(round(self.min_gap_ms * rate / 1000.0))
        segs = []
        cur_s, cur_e = starts[0], ends[0]
        for s, e in zip(starts[1:], ends[1:]):
            if s - cur_e <= min_gap_samp:
                cur_e = max(cur_e, e)
            else:
                segs.append((cur_s, cur_e))
                cur_s, cur_e = s, e
        segs.append((cur_s, cur_e))

        # długości poszczególnych pików (bez przerw)
        durs_ms = [(e - s) * 1000.0 / rate for s, e in segs]

        return [float(d) for d in durs_ms]

    def decode2morse(self, boundary_ms: float = 300.0):
        """
        Mapuje czasy trwania na '.' lub '_' wg progu boundary_ms.
        Zwraca string Morse'a dla pojedynczej litery (np. ".-" lub "_").
        """
        sample = self.analyze()
        if sample is None:
            return ""

        # jeden pik
        if isinstance(sample, float):
            return "." if sample <= boundary_ms else "_"

        # wiele pików: czasy trwania każdego
        out = []
        for dur in sample:
            out.append("." if dur <= boundary_ms else "_")
        return "".join(out)

    def decode2letter(self) -> str:
        """
        Mapuje kod Morse'a na literę. Zwraca '?' gdy brak dopasowania.
        """
        morse = self.decode2morse()
        if not morse:
            return "?"
        return self.morse_to_letter.get(morse, "?")


test = MorseWAVDecoderLetter("D.wav")
print(test.analyze())
print(test.decode2morse())
print(test.decode2letter())