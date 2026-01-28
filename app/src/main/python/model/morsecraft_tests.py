import re
import sys
from pathlib import Path

import pytest

ROOT = Path(__file__).resolve().parents[1]
PYTHON_SRC = ROOT / "app" / "src" / "main" / "python" / "model"
sys.path.insert(0, str(PYTHON_SRC))

import morsecoder
import morsecoder_words
import morsecoder_sentence
import morsepresenter


def test_random_letter_in_alphabet():
    letter = morsecoder.random_letter()
    assert letter in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"


def test_check_l1_accepts_correct_code():
    assert morsecoder.check_l1("A", "._") is True


def test_check_l1_rejects_incorrect_code():
    assert morsecoder.check_l1("A", "__") is False


def test_reveal_l1_returns_expected_code():
    assert morsecoder.reveal_l1("S") == "..."


def test_reveal_l1_returns_empty_for_unknown():
    assert morsecoder.reveal_l1("?") == ""


def test_random_word_is_three_letters():
    word = morsecoder_words.random_word()
    assert len(word) == 3
    assert word.isalpha()


def test_check_l2_accepts_correct_word_code():
    assert morsecoder_words.check_l2("DOG", "_.. ___ __.") is True


def test_check_l2_rejects_incorrect_word_code():
    assert morsecoder_words.check_l2("DOG", "_.. .._. __.") is False


def test_reveal_l2_returns_expected_code():
    assert morsecoder_words.reveal_l2("SOS") == "... ___ ..."


def test_reveal_l2_ignores_non_letters():
    assert morsecoder_words.reveal_l2("D-O-G") == "_.. ___ __."


def test_check_l2_raises_for_wrong_length():
    with pytest.raises(ValueError):
        morsecoder_words.check_l2("TOO LONG", "")


def test_check_l2_raises_for_non_alpha():
    with pytest.raises(ValueError):
        morsecoder_words.check_l2("D0G", "_.. ___ __.")


def test_random_sentence_has_three_words():
    sentence = morsecoder_sentence.random_sentence()
    words = sentence.split()
    assert len(words) == 3


def test_random_sentence_words_length_range():
    sentence = morsecoder_sentence.random_sentence()
    assert all(3 <= len(word) <= 4 for word in sentence.split())


def test_check_l3_accepts_correct_sentence_code():
    sentence = "DOG CAT KOT"
    code = "_.. ___ __.  _._. ._ _  _._ ___ _"
    assert morsecoder_sentence.check_l3(sentence, code) is True


def test_check_l3_rejects_incorrect_sentence_code():
    sentence = "DOG CAT KOT"
    code = "_.. ___ __.  _._. ._ _  _._ ___ ."
    assert morsecoder_sentence.check_l3(sentence, code) is False


def test_reveal_l3_returns_expected_code():
    sentence = "DOG CAT"
    assert morsecoder_sentence.reveal_l3(sentence) == "_.. ___ __.  _._. ._ _"


def test_reveal_l3_returns_empty_for_empty_input():
    assert morsecoder_sentence.reveal_l3("") == ""


def test_check_l3_raises_for_wrong_word_count():
    with pytest.raises(ValueError):
        morsecoder_sentence.check_l3("DOG CAT", "_.. ___ __.")


def test_check_l3_raises_for_invalid_word_length():
    with pytest.raises(ValueError):
        morsecoder_sentence.check_l3("DOGES CAT KOT", "")


def test_presenter_encodes_single_word():
    presenter = morsepresenter.presenter("SOS")
    assert str(presenter) == "... ___ ..."


def test_presenter_encodes_multiple_words_with_separator():
    presenter = morsepresenter.presenter("A B")
    assert str(presenter) == "._ / _..."


def test_presenter_ignores_non_alpha_characters():
    presenter = morsepresenter.presenter("A! B?")
    assert str(presenter) == "._ / _..."


def test_presenter_handles_lowercase_input():
    presenter = morsepresenter.presenter("sos")
    assert str(presenter) == "... ___ ..."


def test_presenter_preserves_word_count():
    presenter = morsepresenter.presenter("A B C")
    assert str(presenter).count("/") == 2


def test_reveal_l2_uppercases_input():
    assert morsecoder_words.reveal_l2("dog") == "_.. ___ __."


def test_reveal_l3_uppercases_input():
    assert morsecoder_sentence.reveal_l3("dog cat kot") == "_.. ___ __.  _._. ._ _  _._ ___ _"


def test_random_sentence_contains_only_letters_and_spaces():
    sentence = morsecoder_sentence.random_sentence()
    assert re.fullmatch(r"[A-Z ]+", sentence) is not None