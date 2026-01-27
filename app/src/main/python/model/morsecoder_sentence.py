import random


class SentenceRandomizer:
    def __init__(self):
        self.words = [
            "DOG", "CAT", "CAN", "HAD", "WAS", "ALL", "SOK", "BAR", "SOS",
            "KOT", "KOS", "JAN", "OLA", "LIS", "LAS", "DOM", "KOS", "RYB",
            "SAD", "NOC", "DZI", "EKO", "SEN", "PIE", "KRA", "DYM"
        ]

    def rando_sentence(self) -> str:
        pool = [w for w in self.words if 3 <= len(w) <= 4]
        if len(pool) < 3:
            pool = [w for w in self.words]
        words = random.sample(pool, k=3)
        return " ".join(word.upper() for word in words)


class MorseCoderL3Sentence(SentenceRandomizer):
    MORSE = {
        "A": "._", "B": "_...", "C": "_._.", "D": "_..", "E": ".",
        "F": ".._.", "G": "__.", "H": "....", "I": "..", "J": ".___",
        "K": "_._", "L": "._..", "M": "__", "N": "_.", "O": "___",
        "P": ".__.", "Q": "__._", "R": "._.", "S": "...", "T": "_",
        "U": ".._", "V": "..._", "W": ".__", "X": "_.._", "Y": "_.__",
        "Z": "__.."
    }
    WORD_SEP = "  "

    def __init__(self, sentence: str, answer: str, letter_sep: str = " "):
        super().__init__()
        self.sentence = (sentence or "").upper().strip()
        self.answer = answer or ""
        self.letter_sep = letter_sep

        self.encoded = self.encode_sentence(self.sentence)

    def encode_word(self, word: str) -> str:
        if not (3 <= len(word) <= 4):
            raise ValueError("L3 obsługuje tylko słowa 3-4 literowe.")
        if any(ch not in self.MORSE for ch in word.upper()):
            raise ValueError("Słowo zawiera znaki spoza A-Z.")
        return self.letter_sep.join(self.MORSE[ch] for ch in word.upper())

    def encode_sentence(self, sentence: str) -> str:
        words = [part for part in sentence.split() if part]
        if len(words) != 3:
            raise ValueError("L3 obsługuje tylko zdania 3-wyrazowe.")
        encoded_words = [self.encode_word(word) for word in words]
        return self.WORD_SEP.join(encoded_words)

    def coder_l3(self) -> bool:
        return self.answer == self.encoded

    @classmethod
    def reveal(cls, sentence: str, letter_sep: str = " ") -> str:
        w = (sentence or "").upper().strip()
        words = [part for part in w.split() if part]
        if not words:
            return ""
        encoded_words = []
        for word in words:
            codes = [cls.MORSE.get(ch, "") for ch in word if ch in cls.MORSE]
            encoded_words.append(letter_sep.join(codes))
        return cls.WORD_SEP.join(encoded_words)

    def __str__(self):
        return str(self.coder_l3())


def random_sentence() -> str:
    return SentenceRandomizer().rando_sentence()


def check_l3(sentence: str, answer: str) -> bool:
    return MorseCoderL3Sentence(sentence, answer).coder_l3()


def reveal_l3(sentence: str) -> str:
    return MorseCoderL3Sentence.reveal(sentence)