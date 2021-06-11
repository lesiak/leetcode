package day7_reverse_integer;

public class Day7Solution {
    public static void main(String[] args) {
        //System.out.println(reverse(321));
        //System.out.println(reverse(120));
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        var result = 0;
        var nextResult = 0;
        while (x != 0) {
            nextResult = result;
            nextResult *= 10;
            nextResult += x % 10;
            if (nextResult / 10 != result) {
                return 0;
            }
            else {
                result = nextResult;
            }
            x /= 10;
        }
        return result;
    };
}
