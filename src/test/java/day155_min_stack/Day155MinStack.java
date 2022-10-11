package day155_min_stack;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

record StackElem(int value, int currentMin) {
}

class MinStack {

    private final List<StackElem> stack = new ArrayList<>();

    public void push(int val) {
        var currentMin = (stack.isEmpty()) ? val : Math.min(getMin(), val);
        stack.add(new StackElem(val, currentMin));
    }

    public void pop() {
        // Delete last element by passing index
        int index = stack.size() - 1;
        stack.remove(index);
    }

    public int top() {
        int index = stack.size() - 1;
        return stack.get(index).value();
    }

    public int getMin() {
        int index = stack.size() - 1;
        return stack.get(index).currentMin();
    }
}

public class Day155MinStack {
    @Test
    void testStack() {
        MinStack obj = new MinStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.pop();
        int top = obj.top();
        int min = obj.getMin();
        Assertions.assertThat(top).isEqualTo(2);
        Assertions.assertThat(min).isEqualTo(1);
    }
}
