def mySqrt(x: int) -> int:
    left, right = 0, x + 1
    while left < right:
        mid = left + (right - left) // 2
        print(f"left: {left} mid: {mid} right: {right}")
        if mid * mid > x:
            right = mid
        else:
            left = mid + 1
    return left - 1  # `left` is the minimum k value, `k - 1` is the answer

def test_2147395599():
    assert mySqrt(2147395599) == 46339