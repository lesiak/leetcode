package day35_search_insert_position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day35SearchInsertPosition {
    @Test
    void testMiddle1() {
        var nums = new int[]{1,3,5,6};
        assertThat(searchInsert(nums, 5)).isEqualTo(2);
    }

    @Test
    void testMiddle2() {
        var nums = new int[]{1,3,5,6};
        assertThat(searchInsert(nums, 2)).isEqualTo(1);
    }

    @Test
    void testEnd() {
        var nums = new int[]{1,3,5,6};
        assertThat(searchInsert(nums, 7)).isEqualTo(4);
    }

    public int searchInsert(int[] nums, int target) {
        var left = 0;
        var right = nums.length;

        while (left < right) {
            var mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
