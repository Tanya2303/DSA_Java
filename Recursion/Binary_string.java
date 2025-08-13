package Recursion;
import java.util.Scanner;
public class Binary_string {
    // Print all binary strings of length n without consecutive 1s
    public static void printBinaryStrings(int n, int lastplace, String str) {
        if (n == 0) {
            System.out.println(str); // Base case: print the binary string
            return;
        }
        printBinaryStrings(n - 1, 0, str + "0"); // Place 0
        if(lastplace == 0) {
            printBinaryStrings(n - 1, 1, str + "1"); // Place 1
        }
    }
    public static void main(String[] args) {
        System.out.print("Enter the length of binary strings (n): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Binary strings of length " + n + " without consecutive 1s:");
        printBinaryStrings(n, 0, ""); // Start with an empty string and last place as 0
        sc.close();
    }
}
