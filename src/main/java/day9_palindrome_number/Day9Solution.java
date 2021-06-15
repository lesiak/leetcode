package day9_palindrome_number;

public class Day9Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(12321));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x % 10 == 0 && x != 0) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

}
