class MorsePresenter:
    MORSE = {
        "A": "._", "B": "_...", "C": "_._.", "D": "_..", "E": ".",
        "F": ".._.", "G": "__.", "H": "....", "I": "..", "J": ".___",
        "K": "_._", "L": "._..", "M": "__", "N": "_.", "O": "___",
        "P": ".__.", "Q": "__._", "R": "._.", "S": "...", "T": "_",
        "U": ".._", "V": "..._", "W": ".__", "X": "_.._", "Y": "_.__",
        "Z": "__.."
    }

    def __init__(self, value: str, letter_sep: str = " ", word_sep: str = " / "):
        super().__init__()
        self.value = value or ""
        self.letter_sep = letter_sep
        self.word_sep = word_sep

    def encode_letters(self, word: str) -> str:
        letters = [self.MORSE[ch] for ch in word if ch in self.MORSE]
        return self.letter_sep.join(letters)

    def encode_words(self, text: str) -> str:
        words = (text or "").upper().split()
        encoded_words = [self.encode_letters(word) for word in words]
        return self.word_sep.join(encoded_words)

    def __str__(self):
        return str(self.encode_words(self.value))

def presenter(text) -> MorsePresenter:
    return MorsePresenter(text)
