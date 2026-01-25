import random

class Randomizer:
    def __init__(self):
        self.letters = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")

    def rando_letter(self) -> str:
        return random.choice(self.letters)

class MorseCoderL1(Randomizer):
    MORSE = {
        "A": "._", "B": "_...", "C": "_._.", "D": "_..", "E": ".",
        "F": ".._.", "G": "__.", "H": "....", "I": "..", "J": ".___",
        "K": "_._", "L": "._..", "M": "__", "N": "_.", "O": "___",
        "P": ".__.", "Q": "__._", "R": "._.", "S": "...", "T": "_",
        "U": ".._", "V": "..._", "W": ".__", "X": "_.._", "Y": "_.__",
        "Z": "__.."
    }

    def __init__(self, letter: str, answer: str):
        super().__init__()
        self.letter = letter
        self.answer = answer
        self.encoded = self.MORSE.get(self.letter, "")

    def coder_l1(self) -> bool:
        return self.answer == self.encoded

    @classmethod
    def reveal(cls, letter: str) -> str:
        return cls.MORSE.get(letter, "")

    def __str__(self):
        return str(self.coder_l1())

def random_letter() -> str:
    return Randomizer().rando_letter()

def check_l1(letter: str, answer: str) -> bool:
    return MorseCoderL1(letter, answer).coder_l1()

def reveal_l1(letter: str) -> str:
    return MorseCoderL1.reveal(letter)