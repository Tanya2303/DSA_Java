package Searching_and_sorting;

public class Merge_sort {

    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);      // Sort left half
            mergeSort(arr, mid + 1, end);    // Sort right half
            merge(arr, start, mid, end);     // Merge sorted halves
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int temp[] = new int[end - start + 1];  // Temporary array
        int i = start, j = mid + 1, k = 0;

        // Merge the two halves into temp[]
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy remaining elements from left half
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements from right half
        while (j <= end) {
            temp[k++] = arr[j++];
        }

        // Copy temp[] back to arr[]
        for (i = start; i <= end; i++) {
            arr[i] = temp[i - start];
        }
    }

    // Example usage
    public static void main(String[] args) {
        int arr[] = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
