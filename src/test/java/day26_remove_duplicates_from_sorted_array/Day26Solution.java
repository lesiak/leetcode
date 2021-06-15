package day26_remove_duplicates_from_sorted_array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class Day26Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int first = 0;
        int checkPos = 1;

        while (checkPos < nums.length) {
            if (nums[first] != nums[checkPos]) {
                first++;
                nums[first] = nums[checkPos];
            }
            checkPos++;
        }
        return first + 1;
    }



    public int removeDuplicates2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        //int[] uniqNums = new int[];
        HashSet<Integer> uniqNums = new HashSet<Integer>();
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(!uniqNums.contains(nums[i])){
                uniqNums.add(nums[i]);
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    @Test
    void testRemoveDuplicatesA() {
        int[] nums = {1,1,1,2};
        var uniqCount = removeDuplicates(nums);
        Assertions.assertThat(uniqCount).isEqualTo(2);
        Assertions.assertThat(nums[0]).isEqualTo(1);
        Assertions.assertThat(nums[1]).isEqualTo(2);
    }

    @Test
    void testRemoveDuplicatesB() {
        int[] nums = {1,1,2, 2, 2, 2, 4, 5,5};
        var uniqCount = removeDuplicates(nums);
        Assertions.assertThat(uniqCount).isEqualTo(4);
        Assertions.assertThat(nums[0]).isEqualTo(1);
        Assertions.assertThat(nums[1]).isEqualTo(2);
        Assertions.assertThat(nums[2]).isEqualTo(4);
        Assertions.assertThat(nums[3]).isEqualTo(5);
    }

    @Test
    void testRemoveDuplicates2A() {
        int[] nums = {1,1,1,2};
        var uniqCount = removeDuplicates2(nums);
        Assertions.assertThat(uniqCount).isEqualTo(2);
        Assertions.assertThat(nums[0]).isEqualTo(1);
        Assertions.assertThat(nums[1]).isEqualTo(2);
    }

    @Test
    void testRemoveDuplicates2B() {
        int[] nums = {1,1,2, 2, 2, 2, 4, 5,5};
        var uniqCount = removeDuplicates2(nums);
        Assertions.assertThat(uniqCount).isEqualTo(4);
        Assertions.assertThat(nums[0]).isEqualTo(1);
        Assertions.assertThat(nums[1]).isEqualTo(2);
        Assertions.assertThat(nums[2]).isEqualTo(4);
        Assertions.assertThat(nums[3]).isEqualTo(5);
    }
}
