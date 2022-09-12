package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeParserTest {
    @Test
    void testParseInput() {
        var input = Arrays.asList(3,9,20,null,null,15,7);
        var root = TreeParser.parseInput(input);
        assertThat(root.val).isEqualTo(3);
        assertThat(root.left.val).isEqualTo(9);
        assertThat(root.left.left).isNull();
        assertThat(root.left.right).isNull();
        assertThat(root.right.val).isEqualTo(20);
        assertThat(root.right.left.val).isEqualTo(15);
        assertThat(root.right.left.left).isNull();
        assertThat(root.right.left.right).isNull();
        assertThat(root.right.right.val).isEqualTo(7);
        assertThat(root.right.right.left).isNull();
        assertThat(root.right.right.right).isNull();
    }
}
