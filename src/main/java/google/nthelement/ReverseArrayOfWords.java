package google.nthelement;


import java.util.Arrays;

public class ReverseArrayOfWords {
    static void swap(char arr[], int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverseArray(char[] input, int firstIndex, int lastIndex) {
        while (firstIndex < lastIndex) {
            swap(input, firstIndex++, lastIndex--);
        }
    }

    public static int findEndOfWord(char[] input, int firstLetterIndex) {
        int lastLetterIndex = firstLetterIndex;
        while (true) {
            if (lastLetterIndex == input.length - 1) {
                return lastLetterIndex;
            }
            else if (input[lastLetterIndex+1] == ' ') {
                return lastLetterIndex;
            } else {
                lastLetterIndex ++;
            }

        }

    }

    public static void reverseWords(char[] input) {
        reverseArray(input, 0 , input.length -1);

        int firstWordIndex = 0;
        int lastWordIndex = findEndOfWord(input, firstWordIndex);
        reverseArray(input, firstWordIndex, lastWordIndex);
        while (lastWordIndex < input.length - 1) {
            firstWordIndex = lastWordIndex + 2;
            lastWordIndex = findEndOfWord(input, firstWordIndex);
            reverseArray(input, firstWordIndex, lastWordIndex);
        }

    }

    public static void main(String[] args) {
        char[] input = {'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ',
                'm', 'a', 'k', 'e', 's', ' ',
                'p', 'e', 'r', 'f', 'e', 'c', 't'};
        reverseWords(input);
        System.out.println(Arrays.toString(input));

    }


}
