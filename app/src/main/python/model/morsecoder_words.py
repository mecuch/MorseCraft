import random

class WordRandomizer:
    def __init__(self):
        self.words = [
            "DOG", "CAT", "CAN", "HAD", "WAS", "ALL",  "SOK", "BAR", "SOS", "KOT", "KOS", "JAN", "OLA"
        ]

    def rando_word(self) -> str:
        pool = [w for w in self.words]
        if not pool:
            pool = self.words
        return random.choice(pool).upper()

class MorseCoderL2Words(WordRandomizer):
    MORSE  = {
        "A": "._", "B": "_...", "C": "_._.", "D": "_..", "E": ".",
        "F": ".._.", "G": "__.", "H": "....", "I": "..", "J": ".___",
        "K": "_._", "L": "._..", "M": "__", "N": "_.", "O": "___",
        "P": ".__.", "Q": "__._", "R": "._.", "S": "...", "T": "_",
        "U": ".._", "V": "..._", "W": ".__", "X": "_.._", "Y": "_.__",
        "Z": "__.."
    }

    def __init__(self, word: str, answer: str, letter_sep: str = " "):
        super().__init__()
        self.word = (word or "").upper().strip()
        self.answer = answer or ""
        self.letter_sep = letter_sep

        self.encoded = self.encode_word(self.word)

    def encode_word(self, word: str) -> str:
        if len(word) != 3:
            raise ValueError("L2 obsługuje tylko słowa 3-literowe.")
        if any(ch not in self.MORSE for ch in word):
            raise ValueError("Słowo zawiera znaki spoza A-Z.")
        return self.letter_sep.join(self.MORSE[ch] for ch in word)

    def coder_l2(self) -> bool:
        return self.answer == self.encoded

    @classmethod
    def reveal(cls, word: str, letter_sep: str = " ") -> str:
        w = (word or "").upper().strip()
        codes = [cls.MORSE.get(ch, "") for ch in w if ch in cls.MORSE]
        return letter_sep.join(codes)

    def __str__(self):
        return str(self.coder_l2())

def random_word() -> str:
    return WordRandomizer().rando_word()

def check_l2(word: str, answer: str) -> bool:
    return MorseCoderL2Words(word, answer).coder_l2()

def reveal_l2(word: str) -> str:
    return MorseCoderL2Words.reveal(word)

#self.words = ["DOG", "CAT", "CAN", "HAD", "WAS", "ALL",  "SOK", "BAR", "SOS", "KOT", "KOS", "JAN", "OLA"]