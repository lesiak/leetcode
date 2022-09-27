from typing import List


def test_no_carry():
    assert plus_one([1, 2, 3]) == [1, 2, 4]


def test_carry():
    assert plus_one([1, 2, 9]) == [1, 3, 0]


def test_carry_on_first_digit():
    assert plus_one([9, 9, 9]) == [1, 0, 0, 0]


def plus_one(digits: List[int]) -> List[int]:
    ret = []
    carry = 1
    for i in range(len(digits) - 1, -1, -1):
        curr = digits[i]
        curr += carry
        if curr >= 10:
            curr -= 10
            carry = 1
        else:
            carry = 0
        ret.append(curr)

    if carry == 1:
        ret.append(carry)

    ret.reverse()
    return ret
