package day189_rotate_array;

import java.util.Arrays;

public class Day189Solution {
    public static void main(String[] args) {

        //rotateRight2(arr, 3);
        test(new int[]{1, 2, 3, 4, 5, 6, 7, 8 ,9, 10}, 3);
     //   test(new int[]{1, 2, 3}, 2);
        //test(new int[]{-1,-100,3,99}, 2);
        //test(new int[]{-1}, 2);
     //   test(new int[]{1, 2, 3, 4, 5, 6}, 4);
      //  test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 8);

    }

    static void test(int[] arr, int k) {
        System.out.println("Rotating array:" + Arrays.toString(arr));
        rotateRightAnnotated(arr, k);
        System.out.println(Arrays.toString(arr));
    }

    static void rotateLeft(int[] a, int middle) {
        int first = 0;
        int next = middle;
        while (first != next) {
            swap(a, first, next);
            ++first;
            ++next;
            if (next == a.length) {
                next = middle;
            } else if (first == middle) {
                middle = next;
            }
        }
    }

    static void rotateLeftAnnotated(int[] a, int middle) {
        int first = 0;
        int next = middle;
        while (first != next) {
            swap(a, first, next);
            ++first;
            ++next;
            if (next == a.length) {
                System.out.println("A first at: " + first + " next at a.len: " + Arrays.toString(a));
                System.out.println("A next <- "  + middle);
                next = middle;
            } else if (first == middle) {
                System.out.println("B first at middle: " + middle + " next at:" + next + " " + Arrays.toString(a));
                System.out.println("B Middle <- "  + next);
                middle = next;
            }
        }
        System.out.println("Finished first: " + first + " next: " + next);
    }



    static void rotateRightAnnotated(int[] a, int k) {
        rotateLeft(a, a.length - k);
////        if (k > a.length) {
////            k = k % a.length;
////        }
////        if (k == 0) {
////            return;
////        }
//        int right = a.length -1;
//        int middle = right - k;
//        int left = middle;
//
//        while (left != right) {
//            swap(a, left, right);
//            --left;
//            --right;
//            if (left ==0) {
//                System.out.println("A left at: " + left + " right : " + right + " " + Arrays.toString(a));
//                System.out.println("A right <- "  + middle);
//                right = middle;
//            } else if (right == middle) {
//                System.out.println("B left at middle: " + middle + " right at:" + right + " " + Arrays.toString(a));
//                System.out.println("B Middle <- "  + left);
//                middle = left;
//            }
//        }
//        System.out.println("Finished left: " + left + " right: " + right);
    }

    private static void swap(int[] a, int i1, int i2) {
        int temp = a[i2];
        a[i2] = a[i1];
        a[i1] = temp;
    }
}
