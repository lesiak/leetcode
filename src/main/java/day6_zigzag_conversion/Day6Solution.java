package day6_zigzag_conversion;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day6Solution {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        var rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; ++i) {
            rows[i] = new StringBuilder();
        }
        var curRow = 0;
        int dir = 1;
        for (int i = 0; i < s.length(); ++i) {
            rows[curRow].append(s.charAt(i));
            curRow += dir;
            if (curRow == -1) {
                curRow = 1;
                dir = 1;
            }
            else if (curRow == numRows) {
                curRow = numRows - 2;
                dir = -1;
            }
        }
        return Arrays.stream(rows).map(StringBuilder::toString).collect(Collectors.joining());
    }
}
