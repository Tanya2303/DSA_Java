package Recursion;
import java.util.Scanner;
public class Sum_of_no {

    public static int sum(int n) {
        if (n == 0) {
            return 0;
        }
        int snm1 = sum(n - 1);
        int sn = snm1 + n;
        return sn;
    }
    public static void main(String[] args) {
        System.out.print("Enter a number to calculate the sum of numbers from 1 to n:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = sum(n);
        System.out.println("Sum of numbers from 1 to " + n + " is: " + result);
        sc.close();
    }
}
