package Recursion;
import java.util.Scanner;
public class Print_no {

    public static void printDec(int n) {
        if (n == 1) {
            System.out.println(n);
            return;
        }
        System.out.print(n + " ");
        printDec(n - 1);
    }

    public static void printInc(int n) {
        if(n == 1){
            System.out.print(n + " ");
            return;
        }
        printInc(n - 1);
        System.out.print(n + " ");
    }
    public static void main(String[] args) {
        System.out.print("Enter a number:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Printing numbers in descending order:");
        printDec(n);
        System.out.println("\nPrinting numbers in ascending order:");
        printInc(n);
        sc.close();
    }
}
