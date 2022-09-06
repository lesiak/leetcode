package google.nthelement;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MostFrequentInteger {
    public static int mostFrequentInt(int[] input) {
        HashMap<Integer, Integer> numToCount = new HashMap<>();
        for (int x: input) {
            var currentCount = numToCount.getOrDefault(x, 0);
            numToCount.put(x, currentCount + 1);
        }
        var e = numToCount.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElseThrow();
        return e.getKey();
    }

    public static void main(String[] args) {
        int[] values = {1, 1, 3, 4, 3, 3};
        System.out.println(mostFrequentInt(values));
    }
}
