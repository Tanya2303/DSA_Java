import java.util.Scanner;
public class average {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter three integers to calculate their average:");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        // Calculate the average
        int average = (num1 + num2 + num3) / 3;
        System.out.println("The average is: " + average);
        sc.close();

    }
    
}
