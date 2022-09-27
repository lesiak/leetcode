from typing import List


def test_1():
    result = twoSum([3, 2, 4], 6)
    assert result == [1, 2]


def twoSum(nums: List[int], target: int) -> List[int]:
    num_to_pos = {}
    for i, n in enumerate(nums):
        diff = target - n
        diff_index = num_to_pos.get(diff)
        if diff_index is not None:
            return [diff_index, i]
        num_to_pos[n] = i

    return []
