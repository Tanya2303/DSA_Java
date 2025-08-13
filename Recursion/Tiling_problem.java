package Recursion;
import java.util.Scanner;
public class Tiling_problem {
    public static int countWays(int n) {
        if (n == 0 || n == 1) {
            return 1; // Base cases: 1 way for 1 tile, 2 ways for 2 tiles
        }
        int vertical = countWays(n - 1); // Place a vertical tile
        int horizontal = countWays(n - 2); // Place two horizontal tiles
        return vertical + horizontal; // Total ways = vertical + horizontal
    }
    public static void main(String[] args) {
        System.out.print("Enter the length of the wall (n): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ways = countWays(n);
        System.out.println("Number of ways to tile a wall of length " + n + " is: " + ways);
        sc.close();
    }
}
