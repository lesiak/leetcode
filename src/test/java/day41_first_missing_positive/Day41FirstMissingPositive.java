package day41_first_missing_positive;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day41FirstMissingPositive {
    @Test
    void testAllNumsPositiveUnsorted() {
        var nums = new int[]{1, 2, 0};
        assertThat(firstMissingPositive(nums)).isEqualTo(3);
    }

    @Test
    void testOnePositiveNumberNotPresent() {
        var nums = new int[]{3, 4, -1, 1};
        assertThat(firstMissingPositive(nums)).isEqualTo(2);
    }

    @Test
    void testOneNotPresent() {
        var nums = new int[]{7, 8, 9, 11, 12};
        assertThat(firstMissingPositive(nums)).isEqualTo(1);
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
        // (we can ignore those because if all number are > n then we'll simply return 1)
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        // note: all number in the array are now positive, and on the range 1..n+1

        // 2. mark each cell appearing in the array, by converting the index for that number to negative
        for (int i = 0; i < n; i++) {
            // first check if the number in the cell is n+1 or -(n + 1) if it was inverted
            int num = Math.abs(nums[i]);
            if (num == n + 1) {
                continue;
            }
            num--; // -1 for zero index based array (so the number 1 will be at pos 0)
            if (nums[num] > 0) { // prevents double negative operations
                nums[num] = -nums[num];
            }
        }

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }

        // 4. no positive numbers were found, which means the array contains all numbers 1..n
        return n + 1;
    }

    public int firstMissingPositiveSimple(int[] nums) {
        Arrays.sort(nums);
        int ans = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == ans) {
                ans++;
            }
        }
        return ans;
    }

}
