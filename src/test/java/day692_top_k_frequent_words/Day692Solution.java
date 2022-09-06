package day692_top_k_frequent_words;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Day692Solution {
    public static Comparator<Map.Entry<String, Long>> comparingFrequencyAndThenLexical() {
        return (c1, c2) -> {
            var ret = c1.getValue().compareTo(c2.getValue());
            if (ret == 0) {
                return c1.getKey().compareTo(c2.getKey());
            } else {
                return -ret;
            }

        };
    }

    public static Comparator<Map.Entry<String, Integer>> comparingFrequencyAndThenLexicalInt() {
        return (c1, c2) -> {
            var ret = c1.getValue().compareTo(c2.getValue());
            if (ret == 0) {
                return c1.getKey().compareTo(c2.getKey());
            } else {
                return -ret;
            }

        };
    }

    public List<String> topKFrequent(String[] words, int k) {
        var ret = Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(comparingFrequencyAndThenLexical())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return ret;
    }


    public List<String> topKFrequent2(String[] words, int k) {

        HashMap<String, Integer> wordToCount = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            wordToCount.put(words[i], wordToCount.getOrDefault(words[i], 0L) + 1);
        var priorityQueue = new PriorityQueue<Map.Entry<String, Integer>>(
                comparingFrequencyAndThenLexicalInt()
        );
        priorityQueue.addAll(wordToCount.entrySet());

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ret.add(priorityQueue.poll().getKey());
        return ret;
    }


    private class Trie {

        private int count;
        private int low;
        private int high;
        private String s;
        private Trie[] children;

        public Trie() {

        }

        public int insert(String s, int level) {
            if (s.length() == level) {
                if (count == 0) {
                    this.s = s;
                }
                count++;
                return count;
            }

            int index = s.charAt(level) - 'a';

            if (children == null) {
                children = new Trie[26];
                low = high = index;
            }

            if (children[index] == null) {
                children[index] = new Trie();
                if (index < low) {
                    low = index;
                } else if (index > high) {
                    high = index;
                }
            }
            return children[index].insert(s, level + 1);
        }

        public void traverse() {
            if (children != null) {
                for (int i = high; i >= low; i--) {
                    if (children[i] != null) {
                        children[i].traverse();
                    }
                }
            }
            if (count > 0) {
                ListTrie listTrie = new ListTrie(this);
                listTrie.next = listTries[count];
                listTries[count] = listTrie;
            }
        }
    }

    private class ListTrie {

        private Trie trie;
        private ListTrie next;

        public ListTrie(Trie trie) {
            this.trie = trie;
        }
    }

    private ListTrie[] listTries;

    public List<String> topKFrequentTrie(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }

        int max = 0;
        Trie root = new Trie();

        for (String word : words) {
            int count = root.insert(word, 0);
            if (count > max) {
                max = count;
            }
        }

        listTries = new ListTrie[max + 1];
        root.traverse();

        for (int i = max; i >= 0 && k > 0; i--) {
            ListTrie listTrie = listTries[i];
            while (listTrie != null && k > 0) {
                result.add(listTrie.trie.s);
                listTrie = listTrie.next;
                k--;
            }
        }

        return result;
    }

    @Test
    void testTopKFrequent1() {
        var words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        Assertions.assertThat(topKFrequent(words, 2)).containsExactly("i", "love");
    }

    @Test
    void testTopKFrequent2() {
        var words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        Assertions.assertThat(topKFrequent2(words, 2)).containsExactly("i", "love");
    }

    @Test
    void testTopKFrequentTrie() {
        var words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        Assertions.assertThat(topKFrequentTrie(words, 2)).containsExactly("i", "love");
    }

}
