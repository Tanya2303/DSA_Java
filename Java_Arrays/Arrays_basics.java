package Java_Arrays;
import java.util.Scanner;

public class Arrays_basics {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int marks[] = new int[50]; // Declare an array of integers with size 50
        marks[0] = 10; // Assign value to the first element
        marks[1] = 20; // Assign value to the second element
        marks[2] = 30; // Assign value to the third element
        System.out.println("Enter the fourth element of the array:");
        marks[3] = sc.nextInt(); // Assign value to the fourth element from user input

        // Print the values of the first three elements
        System.out.println("First element: " + marks[0]);
        System.out.println("Second element: " + marks[1]);
        System.out.println("Third element: " + marks[2]);
        System.out.println("Fourth element: " + marks[3]);
        // Print the size of the array
        System.out.println("Size of the array: " + marks.length);

        marks[2] = 50; // Change the value of the third element
        System.out.println("Updated third element: " + marks[2]);
    }
}




    
    

