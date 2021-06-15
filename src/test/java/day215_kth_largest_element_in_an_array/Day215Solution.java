package day215_kth_largest_element_in_an_array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Day215Solution {
    // Function to return
    // the sign of the number
    int sign(double x)
    {
        if (x < 0)
            return -1;
        if (x > 0)
            return 1;
        return 0;
    }

    // Function to swap two numbers in an array
    void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Function to return kth smallest number
    int floydRivestSelect(int arr[], int left,
               int right, int k)
    {
        while (right > left) {
            if (right - left > 600) {
                // Choosing a small subarray
                // S based on sampling.
                // 600, 0.5 and 0.5 are
                // arbitrary constants
                int n = right - left + 1;
                int i = k - left + 1;
                double z = Math.log(n);
                double s = 0.5 * Math.exp(2 * z / 3);

                double sd = 0.5 * Math.sqrt(z * s * (n - s) / n)
                        * sign(i - n / 2);

                int newLeft = Math.max(left,
                        (int)(k - i * s / n
                                + sd));

                int newRight = Math.min(right,
                        (int)(k + (n - i) * s / n
                                + sd));

                floydRivestSelect(arr, newLeft, newRight, k);
            }

            // Partition the subarray S[left..right]
            // with arr[k] as pivot
            int t = arr[k];
            int i = left;
            int j = right;
            swap(arr, left, k);
            if (arr[right] > t) {
                swap(arr, left, right);
            }

            while (i < j) {
                swap(arr, i, j);
                i++;
                j--;

                while (arr[i] < t)
                    i++;
                while (arr[j] > t)
                    j--;
            }

            if (arr[left] == t)
                swap(arr, left, j);
            else {
                j++;
                swap(arr, right, j);
            }

            // Adjust the left and right
            // pointers to select the subarray having k
            if (j <= k)
                left = j + 1;
            if (k <= j)
                right = j - 1;
        }
        return arr[k];
    }

    public int findKthLargest(int[] nums, int k) {
        //Arrays.sort(nums);
        //return nums[nums.length - k];
        return floydRivestSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    @Test
    void testFindKthLargest() {
        int[] nums = {3,2,1,5,6,4};
        Assertions.assertThat(findKthLargest(nums, 2)).isEqualTo(5);
    }

    @Test
    void testFindKthLargest2() {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        Assertions.assertThat(findKthLargest(nums, 4)).isEqualTo(4);
    }
}
