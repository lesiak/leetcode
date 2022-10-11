package day151_reverse_words_in_a_string;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day151ReverseWordsInAString {

    @Test
    void testSimple() {
        var s = "the sky is blue";
        var reversed = reverseWords(s);
        assertThat(reversed).isEqualTo("blue is sky the");
    }

    @Test
    void testMultipleStartingAndTrailingSpaces() {
        var s = "     hello world    ";
        var reversed = reverseWords(s);
        assertThat(reversed).isEqualTo("world hello");
    }

    @Test
    void testMultipleInsideSpaces() {
        var s = "hello    world";
        var reversed = reverseWords(s);
        assertThat(reversed).isEqualTo("world hello");
    }


    public String reverseWords(String s) {
        String[] splited = s.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean hasWords = false;
        for (int i = splited.length - 1; i >= 0; i--) {
            if (splited[i].isEmpty()) {
                continue;
            }
            if (hasWords) {
                sb.append(" ");
            }
            sb.append(splited[i]);
            hasWords = true;
        }
        return sb.toString();
    }
}
