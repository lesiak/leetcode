from typing import List


def min_sub_array_len(target: int, nums: List[int]) -> int:
    current_sum = 0
    left = 0
    right = 0
    min_subarray_len = float('inf')

    while right < len(nums):
        current_sum += nums[right]
        while current_sum >= target:
            cur_len = right - left + 1

            min_subarray_len = min(min_subarray_len, cur_len)
            current_sum -= nums[left]
            left += 1
        right += 1
    return 0 if min_subarray_len == float('inf') else min_subarray_len


def test_0():
    target = 7
    nums = [2, 3, 1, 2, 4, 3]
    assert min_sub_array_len(target, nums) == 2
