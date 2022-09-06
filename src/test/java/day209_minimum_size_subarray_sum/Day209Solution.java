package day209_minimum_size_subarray_sum;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class Day209Solution {

    @Test
    void twoSumsEqualToTargetWithDifferentLengths() {
        var nums = new int[]{2, 3, 1, 2, 4, 3};
        var target = 7;
        assertThat(lengthOfMinimalSubarray(nums, target)).isEqualTo(2);
    }


    public static int lengthOfMinimalSubarray(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int minLen = Integer.MAX_VALUE;
        int leftIndex = 0;
        int sum = 0;
        for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
            sum += nums[rightIndex];
            while (sum >= target) {
                minLen = Math.min(minLen, rightIndex - leftIndex + 1);
                sum -= nums[leftIndex++];
            }
        }
        return (minLen != Integer.MAX_VALUE) ? minLen : 0;

    }


}
