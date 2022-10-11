package day334_increasing_triplet_subsequence;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day334IncreasingTripletSubsequence {
    @Test
    void testIncreasing() {
        var input = new int[]{1, 2, 3, 4, 5};
        assertThat(increasingTriplet(input)).isTrue();
    }

    @Test
    void testDecreasing() {
        var input = new int[]{5, 4, 3, 2, 1};
        assertThat(increasingTriplet(input)).isFalse();
    }

    @Test
    void testExistingTriplet1() {
        var input = new int[]{2, 1, 5, 0, 4, 6};
        assertThat(increasingTriplet(input)).isTrue();
    }

    /**
     * We just need to find if the given property exists. I believe some confusion comes from the part where first is set to 5 during the iteration.
     * Notice that the property that we're trying to look for is first < second and since we already found that
     * there existed 10 (first) < 12 (second), we just need to look for something that's greater than both first and second.
     * It doesn't matter if first and second get overridden with other values because we've already "seen" a first < second pair
     * somewhere in the past during the iteration.
     *
     * i = 0: first = 20, second = inf
     * i = 1: first = 20, second = 100
     * i = 2: first = 10, second = 100 (here, we know that there existed some pair of indices with first < second. So, if we find some value a[k] > second (100), then we're done)
     * i = 3: first = 10, second = 12 (same point as above applies here too, kinda. If an element is > 100, a match for i = 2 iteration will also be found as well as current iteration)
     * i = 4: first = 5, second = 12 (apply same logic as above. We just need something > 12 or > 100 (if we do find this, then > 12 is also automatically satisfied) at this point because we've found (20, 100), (10, 12) somewhere in the past)
     * i = 5: first = 5, second = 12 (done! There was an element 13 > second (12) giving us one triplet)
     */
    @Test
    void testExistingTriplet2() {
        var input = new int[]{20, 100, 10, 12, 5, 13};
        assertThat(increasingTriplet(input)).isTrue();
    }


    public boolean increasingTriplet(int[] nums) {
        // optimization for short arrays
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
