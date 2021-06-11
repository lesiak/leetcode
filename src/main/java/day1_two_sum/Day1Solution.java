package day1_two_sum;

import java.util.*;

class Day1Solution {

    public static void main(String[] args) {
        //int[] input = {2,7,11,15};
        //int target = 9;
        int[] input = {3, 2, 4};
        int target = 6;
        int[] result = twoSumOnePass(input, target);
        System.out.println(Arrays.toString(result));
    }

    static public int[] twoSumTwoPass(int[] nums, int target) {
        Map<Integer, Integer> numToPosition = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            numToPosition.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; ++i) {
            var curElem = nums[i];
            var expectedElem = target - curElem;
            var expectedElemPosition = numToPosition.get(expectedElem);
            if (expectedElemPosition != null && expectedElemPosition != i) {
                return new int[]{i, expectedElemPosition};
            }
        }
        return new int[]{-1, -1};
    }

    static public int[] twoSumOnePass(int[] nums, int target) {
        Map<Integer, Integer> numToPosition = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            var curElem = nums[i];
            var expectedElem = target - curElem;
            var expectedElemPosition = numToPosition.get(expectedElem);
            if (expectedElemPosition != null) {
                return new int[]{expectedElemPosition, i};
            } else {
                numToPosition.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}