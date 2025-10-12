import random

class Randomizer:
    def __init__(self):
        self.letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q","R", "S",
                        "T", "U", "V", "W", "X", "Y", "Z"]

    def rando_letter(self) -> str:
        return random.choice(self.letters)


class MorseCoderL1(Randomizer):
    def __init__(self, letter, answer):
        super().__init__()
        self.letter = letter
        self.answer = answer
        self.morse: dict[str, str] = {
            "A": ".-", "B": "-...", "C": "-.-.", "D": "-..", "E": ".",
            "F": "..-.", "G": "--.", "H": "....", "I": "..", "J": ".---",
            "K": "-.-", "L": ".-..", "M": "--", "N": "-.", "O": "---",
            "P": ".--.", "Q": "--.-", "R": ".-.", "S": "...", "T": "-",
            "U": "..-", "V": "...-", "W": ".--", "X": "-..-", "Y": "-.--",
            "Z": "--.."
        }

    def coder_l1(self):
        encoded = self.morse.get(self.letter)
        if self.answer == encoded:
            return True
        else:
            return False

    def __str__(self):
        return str(self.coder_l1())

def random_letter():
    return Randomizer().rando_letter()

def check_l1(letter, answer):
    return MorseCoderL1(letter, answer).coder_l1() 