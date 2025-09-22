package Recursion;
import java.util.Scanner;
public class i_Print_power_optimized {
    
    public static int power(int x, int n) {
        if (n == 0) {
            return 1; // Base case: x^0 = 1
        }
        if (n % 2 == 0) {
            int halfPower = power(x, n / 2);
            return halfPower * halfPower; // x^n = (x^(n/2))^2
        } else {
            return x * power(x, n - 1); // Recursive case: x^n = x * x^(n-1)
        }
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
