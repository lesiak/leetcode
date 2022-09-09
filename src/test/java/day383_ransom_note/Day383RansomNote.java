package day383_ransom_note;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day383RansomNote {
    @Test
    void testOneDifferentLetter() {
        String ransomNote = "a";
        String magazine = "b";
        assertThat(canConstruct(ransomNote, magazine)).isFalse();
    }

    @Test
    void testInsufficientCountOfLettersInMagazine() {
        String ransomNote = "aa";
        String magazine = "ab";
        assertThat(canConstruct(ransomNote, magazine)).isFalse();
    }

    @Test
    void testSufficientCountOfLettersInMagazine() {
        String ransomNote = "aa";
        String magazine = "aab";
        assertThat(canConstruct(ransomNote, magazine)).isTrue();
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> availableLetterCounts = new HashMap<Character, Integer>();
        for (int i = 0, n = magazine.length(); i < n; i++) {
            char c = magazine.charAt(i);
            availableLetterCounts.merge(c, 1, Integer::sum);
        }
        for (int i = 0, n = ransomNote.length(); i < n; i++) {
            char c = ransomNote.charAt(i);
            var currentCount = availableLetterCounts.getOrDefault(c, 0);
            if (currentCount == 0) {
                return false;
            } else {
                availableLetterCounts.put(c, currentCount - 1);
            }
        }
        return true;
    }

}
