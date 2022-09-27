import pytest


def test_natural_number_pow_of_2_exponent():
    assert my_pow(2.0, 8) == 256


def test_natural_number():
    assert my_pow(2.0, 10) == 1024


def test_fraction():
    assert my_pow(2.1, 3) == pytest.approx(9.2610)


def test_negative_exponent():
    assert my_pow(2.0, -2) == 0.25

def test_huge_exponent():
    x = my_pow(0.00001, 2147483647)
    print(x)

def my_pow(x: float, n: int) -> float:
    # if n == 0:
    #     return 1.0
    # elif n > 0:
    #     current_pow = 1
    #     ret = x
    #     while current_pow <= n/2:
    #         ret *= ret
    #         current_pow *= 2
    #         print(current_pow)
    #     rest = my_pow(x, n - current_pow)
    #     return ret * rest
    # elif n < 0:
    #     return my_pow(1.0/x, -n)

    def helper(x, n):
        if x == 0:
            return 0
        if n == 0:
            return 1
        if n == 1:
            return x

        res = helper(x, n // 2)
        res *= res

        return res * x if n % 2 else res

    res = helper(x, abs(n))

    return res if n >= 0 else 1 / res



