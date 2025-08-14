package Backtracking;

public class Backtracking_array {

    public static void changeArray(int[] arr, int index, int value) {
        // Base case: if index is out of bounds, return
        if (index == arr.length) {
            printArray(arr);
            return;
        }

        // Set the value at the current index
        arr[index] = value;
        // Recursive call to fill the next index
        changeArray(arr, index + 1, value + 1);
        // Backtracking step: reset the value
        arr[index] = arr[index] - 2; // Decrementing to show backtracking
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        changeArray(arr, 0, 1);
        printArray(arr);
    }
}
