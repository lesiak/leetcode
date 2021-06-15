package day17_letter_combinations_of_a_phone_number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day17Solution {
    static Map<Character, List<Character>> digitToLetters = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z')
    );



    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return List.of();
        } else {
            var ret = new ArrayList<String>();
            var rest = letterCombinations(digits.substring(1));
            if (rest.isEmpty()) {
                rest = List.of("");
            }
            for (var l : digitToLetters.get(digits.charAt(0))) {
                for(var combRest : rest) {
                    ret.add(l + combRest);
                }
            }
            return ret;
        }

    }

    @Test
    void testLetterCombinations() {
        Assertions.assertThat(letterCombinations("23"))
                .containsExactlyInAnyOrder("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
    }
}
