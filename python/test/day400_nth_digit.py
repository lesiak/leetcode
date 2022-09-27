def test_3():
    assert find_nth_digit(3) == 3


def test_13():
    assert find_nth_digit(13) == 1


def test_800():
    assert find_nth_digit(800) == 1

# First 9 numbers with 1 digit;
# following 90 numbers with 2 digits;
# following 900 numbers with 3 digits;
# ...
# Let's say the number is within rate numbers with k digits
# The left over nth is (n-1)%kth digits of (n-1)/kth number after rate/9
def find_nth_digit(n: int) -> int:
    k = 1
    num_of_numbers_with_k_digits = 9
    while n > k * num_of_numbers_with_k_digits:
        # remove all digits
        n -= k * num_of_numbers_with_k_digits
        k += 1
        num_of_numbers_with_k_digits *= 10

    # target number
    val = num_of_numbers_with_k_digits // 9 + (n - 1) // k
    #         long val=rate/9+(n-1)/k;
    # target digit
    idx = (n - 1) % k
    return int(str(val)[idx])
