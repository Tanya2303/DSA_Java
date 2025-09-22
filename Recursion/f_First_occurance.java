package Recursion;

public class f_First_occurance {
    public static int firstOccurrence(int[] arr, int target, int index) {
        if (index == arr.length) {
            return -1; // Base case: target not found
        }
        if (arr[index] == target) {
            return index; // Found the first occurrence
        }
        return firstOccurrence(arr, target, index + 1); // Recursive call
    }       
    public static void main(String[] args) {
        System.out.print("The given array is: ");
        int[] arr = {1, 2, 3, 4, 5, 3, 7, 8, 3};
        for (int num : arr) {
            System.out.print(num + " ");    
    }
    int target = 3; // Example target
    int result = firstOccurrence(arr, target, 0);
    System.out.println("\nFirst occurrence of " + target + " is at index: " + result);
    }
}
