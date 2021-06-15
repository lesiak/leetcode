package day67_add_binary;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day67Solution {
    static int charToInt(char c) {
        if (c == '0') {
            return 0;
        } else if (c == '1') {
            return 1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String addBinary(String a, String b) {
        var maxLen = Math.max(a.length(), b.length());
        a = "0".repeat(maxLen - a.length()) + a;
        b = "0".repeat(maxLen - b.length()) + b;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = maxLen - 1; i >= 0; --i) {
            var aDigit = charToInt(a.charAt(i));
            var bDigit = charToInt(b.charAt(i));
            sb.append((aDigit + bDigit + carry) % 2);
            carry = (aDigit + bDigit + carry) / 2;
        }
        if (carry == 1) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    @Test
    void testAddBinary1() {
        System.out.println(addBinary("11", "1"));
        //Assertions.assertThat(addBinary("11", "1")).isEqualTo("100");
    }

    @Test
    void testAddBinary2() {
        Assertions.assertThat(addBinary("1010", "1011")).isEqualTo("10101");
    }

    @Test
    void testLongNumbers() {
        var a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        var b = "1";
        Assertions.assertThat(addBinary(a, b)).isEqualTo("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001110");
    }
}
