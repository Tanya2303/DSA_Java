package Java_Arrays;
import java.util.Scanner;
public class Array_part_reverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int array1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Original array:");
        for(int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " "); // Print original array
        }
        System.out.println();
        System.out.print("Enter the start index for reversal:");
        int start = sc.nextInt(); // Input start index
        System.out.print("Enter the end index for reversal:");
        int end = sc.nextInt(); // Input end index
        while(start < end){
            int temp = array1[start]; // Store the value at start index
            array1[start] = array1[end]; // Swap the values at start and end indices
            array1[end] = temp; // Assign the stored value to end index
            start++; // Move start index forward
            end--; // Move end index backward
        }
        System.out.println("Array after reversal:");
        for(int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " "); // Print the modified array
        }
    }
    
}
