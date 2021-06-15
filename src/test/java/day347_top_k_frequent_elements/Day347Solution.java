package day347_top_k_frequent_elements;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.MapEntry;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day347Solution {

    public int[] topKFrequent(int[] nums, int k) {
        var ret = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(Map.Entry::getKey)
                .mapToInt(i -> i)
                .toArray();
        return ret;
    }

    public int[] topKFrequent2(int[] nums, int k) {
//        var numToCount = Arrays.stream(nums)
//                .boxed()
//                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
//                .entrySet();

        HashMap<Integer, Integer> numToCount = new HashMap<>();
        for(int i=0;i<nums.length;i++)
            numToCount.put(nums[i], numToCount.getOrDefault(nums[i], 0) +1);
        var priorityQueue = new PriorityQueue<Map.Entry<Integer, Integer>>(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        );
        priorityQueue.addAll(numToCount.entrySet());
//        var ret =  Stream.generate(priorityQueue::poll)
//                .limit(k)
//                .mapToInt(Map.Entry::getKey)
//                .toArray();
        int[] ret = new int[k];
        for(int i =0;i<k;i++)
            ret[i] = priorityQueue.poll().getKey();
        return ret;
    }




    @Test
    void testTopKFrequent1A() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Assertions.assertThat(topKFrequent(nums, 2)).containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void testTopKFrequent1B() {
        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
        Assertions.assertThat(topKFrequent(nums, 2)).containsExactlyInAnyOrder(-1, 2);
    }


    @Test
    void testTopKFrequent2() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Assertions.assertThat(topKFrequent2(nums, 2)).containsExactly(1, 2);
    }

    @Test
    void testTopKFrequent2B() {
        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
        Assertions.assertThat(topKFrequent2(nums, 2)).containsExactlyInAnyOrder(-1, 2);
    }

}

