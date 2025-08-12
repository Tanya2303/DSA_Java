package Recursion;
import java.util.Scanner;
public class Fibonacci_series {
    public static int fibonacci(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        int fnm1 = fibonacci(n - 1);
        int fnm2 = fibonacci(n - 2);
        int fn = fnm1 + fnm2;
        return fn;
    }
    public static void main(String[] args) {
        System.out.print("Enter a number to calculate the Fibonacci series up to n:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print("Fibonacci series up to " + n + ": ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        sc.close();
    }
    
}
