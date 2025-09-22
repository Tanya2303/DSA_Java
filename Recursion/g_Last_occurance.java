package Recursion;

public class g_Last_occurance {
    public static int lastOccurrence(int[] arr, int target, int i) {
        if( i == arr.length) {
            return -1; // Base case: target not found
        }
        int isFound = lastOccurrence(arr, target, i + 1); // Recursive call
        if (isFound == -1 && arr[i] == target) {
            return i;
        }
        return isFound;
    }       
    public static void main(String[] args) {
        System.out.print("The given array is: ");
        int[] arr = {8,3,6,9,5,10,2,5,3};
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\nLast occurrence of 3 is at index: ");
        System.out.println(lastOccurrence(arr,5,0));
}
}
