package day3_length_of_longest_substring;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Day3Solution {

    @Test
    void repeatedLongestStringOfLen3() {
        assertThat(lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
    }

    @Test
    void longestStringWithPart() {
        assertThat(lengthOfLongestSubstring("abcbefg")).isEqualTo(5);
    }

    @Test
    void repeatedLongestStringOfLen1() {
        assertThat(lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
    }

    @Test
    void singleLongestStringOfLen3() {
        assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    }

    @Test
    void singleLongestStringOfLen2() {
        assertThat(lengthOfLongestSubstring("au")).isEqualTo(2);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        LinkedHashSet<Character> currentSet = new LinkedHashSet<>();
        currentSet.add(s.charAt(0));
        int currentMax = 1;
        for (int last = 1; last < s.length(); ++last) {
            var lastChar = s.charAt(last);
            if (!currentSet.contains(lastChar)) {
                currentSet.add(lastChar);
                if (currentSet.size() > currentMax) {
                    currentMax = currentSet.size();
                }
            } else {
                // cleanup the beginning of the sliding window
                // after the cleanup, the currentSet ends with lastChar
                var it = currentSet.iterator();
                while (it.next() != lastChar) {
                    it.remove();
                }
                it.remove();
                currentSet.add(lastChar);
            }
        }
        return currentMax;
    }


}
