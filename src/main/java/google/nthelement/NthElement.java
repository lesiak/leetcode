package google.nthelement;

import java.util.Arrays;

public class NthElement {

    public static int minElement(int[] data) {
        if (data.length == 0) {
            return -1;
        }
        int min = data[0];
        for (int i = 1; i < data.length; ++i) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

    static void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int lomutpoPartitioning(int[] data, int firstIndex, int lastIndex) {
        int pivotIndex = lastIndex;
        int pivot = data[pivotIndex];


        int storeIndex  = firstIndex;
        for (int i = firstIndex; i < lastIndex -1; ++i) {
            if (data[i] < pivot) {
                swap(data, i, storeIndex);
                storeIndex++;
            }
        }
        if (data[storeIndex] > pivot) {
            swap(data, pivotIndex, storeIndex);
        }
        return storeIndex;
       // System.out.println("number of elems less than pivot: " + (storeIndex + 1));
    }

    public static int kthElement(int[] data, int firstIndex, int lastIndex, int k) {
        System.out.println("firstIndex:" + firstIndex +  " lastIndex:" + lastIndex);

        if (data.length == 0) {
            throw new IllegalArgumentException("data must not be empty");
        } else if (firstIndex == lastIndex){
            System.out.println("One element: at[" + firstIndex + "]");
            return data[firstIndex];
        }
        int pivotedIndex = lomutpoPartitioning(data, firstIndex, lastIndex);

        System.out.println("paritioned: " + Arrays.toString(data));
        System.out.println("pivot pos: " + pivotedIndex);
        System.out.println();
        if (k == pivotedIndex) {
            return data[pivotedIndex];
        } else if (k < pivotedIndex) {
            return kthElement(data, firstIndex, pivotedIndex - 1, k);
        } else {
            return kthElement(data, pivotedIndex + 1, lastIndex, k);
        }
//        int min = data[0];
//        for (int i = 1; i < data.length; ++i) {
//            if (data[i] < min) {
//                min = data[i];
//            }
//        }

    }

    public static int kthElement(int[] data, int k) {
        return kthElement(data, 0, data.length - 1, k-1);
    }

    public static void main(String[] args) {
        int[] data = {1, 6, 3, 9, 8, 5};
        System.out.println(minElement(data));
        System.out.println(kthElement(data, 5));
    }
}
