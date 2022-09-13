package day69_sqrt;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day69Sqrt {
    @Test
    void test0() {
        assertThat(mySqrt(0)).isEqualTo(0);
    }

    @Test
    void test1() {
        assertThat(mySqrt(1)).isEqualTo(1);
    }

    @Test
    void test4() {
        assertThat(mySqrt(4)).isEqualTo(2);
    }

    @Test
    void test8() {
        assertThat(mySqrt(8)).isEqualTo(2);
    }

    @Test
    void test2147395599() {
        assertThat(mySqrt(2147395599)).isEqualTo(46339);
    }

    @Test
    void test2147483647() {
        assertThat(mySqrt(2147483647)).isEqualTo(46340);
    }



    public int mySqrt(int x) {
        // First we need to search for minimal k satisfying condition k^2 > x,
        // then k - 1 is the answer to the question.
        // Notice that I set right = x + 1 instead of right = x
        // to deal with special input cases like x = 0 and x = 1.

        long left = 0;
        long right = (long)x + 1;

        while (left < right) {
            var mid = left + (right - left) / 2;
            if (mid*mid > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int)(left - 1);

    }
}
