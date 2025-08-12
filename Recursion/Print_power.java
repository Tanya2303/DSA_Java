package Recursion;
import java.util.Scanner;
public class Print_power {
    
    public static int power(int x, int n) {
        if (n == 0) {
            return 1; // Base case: x^0 = 1
        }
        return x * power(x, n - 1); // Recursive case: x^n = x * x^(n-1)
    }

    public static void main(String[] args) {
        System.out.print("Enter the base number (x): ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.print("Enter the exponent (n): ");
        int n = sc.nextInt();
        int result = power(x, n);
        System.out.println(x + " raised to the power of " + n + " is: " + result);
        sc.close();
    }
    
}
