package day27_remove_element;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class Day27Solution {
    public int removeElement(int[] nums, int val) {
        int elemCount = 0;
        int checkPos = 0;

        while (checkPos < nums.length) {
            if (nums[checkPos] != val) {
                nums[elemCount++] = nums[checkPos];
            }
            checkPos++;
        }
        return elemCount;
    }



    @Test
    void testRemoveDuplicatesA() {
        int[] nums = {3,2,2,3};
        var resultLen = removeElement(nums, 3);
        Assertions.assertThat(resultLen).isEqualTo(2);
        Assertions.assertThat(nums[0]).isEqualTo(2);
        Assertions.assertThat(nums[1]).isEqualTo(2);
    }

    @Test
    void testRemoveDuplicatesB() {
        int[] nums = {0,1,2,2,3,0,4,2};
        var resultLen = removeElement(nums, 2);
        Assertions.assertThat(resultLen).isEqualTo(5);
        Assertions.assertThat(nums[0]).isEqualTo(0);
        Assertions.assertThat(nums[1]).isEqualTo(1);
        Assertions.assertThat(nums[2]).isEqualTo(3);
        Assertions.assertThat(nums[3]).isEqualTo(0);
        Assertions.assertThat(nums[4]).isEqualTo(4);
    }

}
