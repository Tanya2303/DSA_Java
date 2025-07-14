package Java_Operators;
import java.util.Scanner;
public class assignment_op {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter an integer:");
        int a = sc.nextInt();

        // Assignment operations
        int b = a; // Simple assignment
        b += 5; // Add and assign
        b -= 2; // Subtract and assign
        b *= 3; // Multiply and assign
        b /= 2; // Divide and assign
        b %= 4; // Modulus and assign

        System.out.println("Value of a: " + a);
        System.out.println("Value of b after assignment operations: " + b);

        sc.close();
    }
}
