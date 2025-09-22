package Recursion;

public class e_Sorted_array {

    public static boolean isSorted(int[] arr, int index) {
        if (index == arr.length - 1) {
            return true; // Base case: last element is always sorted
        }
        if(arr[index] > arr[index + 1]) {
            return false; // If current element is greater than next, not sorted
        }

        return isSorted(arr, index + 1); // Recursive call to check the next element
        

    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5}; // Example sorted array
        boolean result = isSorted(arr, 0);
        System.out.println("Is the array sorted? :" + result);
        int arr2[] = {1, 3, 2, 4, 5}; // Example unsorted array
        boolean result2 = isSorted(arr2, 0);
        System.out.println("Is the second array sorted? :" + result2);
        
    }
}
