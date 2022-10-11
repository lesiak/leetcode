package day451_frequency_sort;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Day451FrequencySort {
    @Test
    void test1() {
        var input = "tree";
        var validAnswers = Set.of("eert", "eetr");
        assertThat(validAnswers).contains(frequencySort2(input));
    }

    public String frequencySort(String s) {
        var letterToCount = s.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        List<Map.Entry<Character, Integer>> lettersInCountOrder = new ArrayList<>(letterToCount.entrySet());
        lettersInCountOrder.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        StringBuilder sb = new StringBuilder();
        for (var e : lettersInCountOrder) {
            var c = e.getKey();
            sb.append(String.valueOf(c).repeat(e.getValue()));
        }
        return sb.toString();
    }

    public String frequencySort2(String s) {
        char[] chrs = s.toCharArray();
        int[] letterToCount = new int[128];
        for (int i = 0; i < chrs.length; i++) {
            char ch = chrs[i];
            letterToCount[ch] += 1;
        }
        var countsToLetters = new TreeMap<Integer, List<Character>>(Comparator.reverseOrder());
        for (char i = 0; i < letterToCount.length; i++) {
            if (letterToCount[i] > 0) {
                var letters = countsToLetters.computeIfAbsent(letterToCount[i], ArrayList::new);
                letters.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (var e : countsToLetters.entrySet()) {
            var times = e.getKey();
            for (var c : e.getValue()) {
                sb.append(String.valueOf(c).repeat(times));
            }
        }
        return sb.toString();
    }
}
