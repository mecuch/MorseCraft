
class MorseLetterPresenter:
    MORSE = {
        "A": "._", "B": "_...", "C": "_._.", "D": "_..", "E": ".",
        "F": ".._.", "G": "__.", "H": "....", "I": "..", "J": ".___",
        "K": "_._", "L": "._..", "M": "__", "N": "_.", "O": "___",
        "P": ".__.", "Q": "__._", "R": "._.", "S": "...", "T": "_",
        "U": ".._", "V": "..._", "W": ".__", "X": "_.._", "Y": "_.__",
        "Z": "__.."
    }

    def __init__(self, letter: str):
        super().__init__()
        self.letter = letter
        self.encoded = self.MORSE.get(self.letter, "")

    def presenter(self):
        return self.encoded

    def __str__(self):
        return str(self.presenter())

def present(letter) -> MorseLetterPresenter:
    return MorseLetterPresenter(letter)
