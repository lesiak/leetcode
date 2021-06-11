package day3_length_of_longest_substring;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class Day3Solution {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));
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
