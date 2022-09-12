package model;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TreeParser {

    public static TreeNode parseInput(Integer... a) {
        return parseInput(Arrays.asList(a));
    }
    public static TreeNode parseInput(List<Integer> input) {
        return parseInput(input, 0);
    }

    public static TreeNode parseInput(List<Integer> input, int index) {

        if (index >= input.size()) {
            return null;
        }
        Integer val = input.get(index);
        if (val == null) {
            return null;
        } else {
            TreeNode left = parseInput(input, 2 * index + 1);
            TreeNode right = parseInput(input, 2 * index + 2);
            return new TreeNode(val, left, right);
        }
    }
}
