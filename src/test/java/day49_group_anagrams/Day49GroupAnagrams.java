package day49_group_anagrams;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 */
public class Day49GroupAnagrams {
    @Test
    void testOneDifferentLetter() {
        var strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        Assertions.assertThat(groupAnagrams(strs).stream().map(Set::copyOf)).containsExactlyInAnyOrder(
                Set.of("bat"),
                Set.of("nat", "tan"),
                Set.of("ate","eat","tea"));
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> normalizedToSourceVals = new HashMap<>();
        for (var s : strs) {
            var sNorm = sort(s);
            normalizedToSourceVals.computeIfAbsent(sNorm, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList(normalizedToSourceVals.values());
    }

    private static String sort(String text) {
        char[] chars = text.toCharArray();

        Arrays.sort(chars);
        return new String(chars);
    }

}
