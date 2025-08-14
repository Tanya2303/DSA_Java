package Searching_and_sorting;

public class Quick_sort {

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(arr, start, end);
            quickSort(arr, start, pivotIndex - 1); // Sort left half
            quickSort(arr, pivotIndex + 1, end);   // Sort right half
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end]; // Choosing the last element as pivot
        int i = start - 1; // Pointer for the smaller element

        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i + 1] and arr[end] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1; // Return the partition index
    }

    // Example usage
    public static void main(String[] args) {
        int arr[] = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
