package day10_regular_expression_matching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

interface CharMatcher {
    boolean matches(char letter);
}

class AnyCharMatcher implements CharMatcher {
    @Override
    public boolean matches(char letter) {
        return true;
    }

    @Override
    public String toString() {
        return ".";
    }
}

class SingleCharMatcher implements CharMatcher {
    private char letter;

    public SingleCharMatcher(char letter) {
        this.letter = letter;
    }

    @Override
    public boolean matches(char letter) {
        return this.letter == letter;
    }

    @Override
    public String toString() {
        return "" + letter;
    }
}

 class StateMachineNode {
    Map<CharMatcher, StateMachineNode> moves = new LinkedHashMap<>();
    boolean isFinal;

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

}

class StateMachine {
    StateMachineNode initalNode = new StateMachineNode();

    boolean matchInput(String s) {
        var toExplore = new ArrayList<StateMachineNode>();
        toExplore.add(initalNode);
        int i = 0;
        for (; i< s.length(); ++i) {
            var letter = s.charAt(i);
            var newFrontier = new ArrayList<StateMachineNode>();
            for (var nodeInFrontier: toExplore) {
                for (Map.Entry<CharMatcher, StateMachineNode> move : nodeInFrontier.moves.entrySet()) {
                    if (move.getKey().matches(letter)) {
                        newFrontier.add(move.getValue());
                    }
                }
            }
            toExplore = newFrontier;
        }
        for (var nodeInFrontier: toExplore) {
            if (nodeInFrontier.isFinal) {
                return true;
            }
        }
        return false;
    }
}



public class Day10Solution {


    public StateMachine parsePattern(String p) {
        StateMachine stateMachine = new StateMachine();
        var currentNodes = List.of(stateMachine.initalNode);
        for (int pos = 0; pos < p.length(); ++pos) {
            var letter = p.charAt(pos);
            Character nextLetter = null;
            if (pos + 1< p.length()) {
                nextLetter = p.charAt(pos + 1);
            }
            if (letter == '.') {
                var matcher = new AnyCharMatcher();
                var newNode = new StateMachineNode();
                if (nextLetter != null && nextLetter == '*') {
                    newNode.moves.put(matcher, newNode);
                    for (var node : currentNodes) {
                        node.moves.put(matcher, newNode);
                    }
                    currentNodes = new ArrayList<>(currentNodes);
                    currentNodes.add(newNode);
                }
                else {
                    for (var node : currentNodes) {
                        node.moves.put(matcher, newNode);
                    }
                    currentNodes = List.of(newNode);

                }
            } else {

                var matcher = new SingleCharMatcher(letter);
                var newNode = new StateMachineNode();
                if (nextLetter != null && nextLetter == '*') {
                    newNode.moves.put(matcher, newNode);
                    for (var node : currentNodes) {
                        node.moves.put(matcher, newNode);
                    }
                    currentNodes = new ArrayList<>(currentNodes);
                    currentNodes.add(newNode);
                }
                else {
                    for (var node : currentNodes) {
                        node.moves.put(matcher, newNode);
                    }
                    currentNodes = List.of(newNode);
                }
            }
            if (nextLetter != null && nextLetter == '*') {
                pos++;
            }

        }

        for (var node : currentNodes) {
            node.setFinal(true);
        }
        return stateMachine;
    }

    public boolean isMatch(String s, String p) {
        var stateMachine = parsePattern(p);
        return stateMachine.matchInput(s);
    }

    @Test
    void notMatchSingleLetterPattern() {
        var s = "aa";
        var pat = "a";
        Assertions.assertThat(isMatch(s, pat)).isFalse();
    }

    @Test
    void matchSingleLetterPattern() {
        var s = "a";
        var pat = "a";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchTwoLetterPattern() {
        var s = "ab";
        var pat = "ab";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchWildcard() {
        var s = "ab";
        var pat = "a.";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesRepeatLetter() {
        var s = "aa";
        var pat = "a*";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesRepeatWildcard() {
        var s = "aa";
        var pat = ".*";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesMultipleRepeats() {
        var s = "aab";
        var pat = "c*a*b";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesPatternLongerThanString() {
        var s = "abc";
        var pat = "abcd";
        Assertions.assertThat(isMatch(s, pat)).isFalse();
    }

    @Test
    void matchesRepeatInTheMiddle() {
        var s = "aaa";
        var pat = "a*a";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesRepeatInTheMiddleAndWildcard() {
        var s = "aaa";
        var pat = "a*.";
        Assertions.assertThat(isMatch(s, pat)).isTrue();
    }

    @Test
    void matchesMultipleWildcardsAgain() {
        var s = "aaba";
        var pat = "ab*a*c*a";
        Assertions.assertThat(isMatch(s, pat)).isFalse();
    }



}
