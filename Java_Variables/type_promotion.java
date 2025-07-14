import java.util.Scanner;

public class type_promotion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter two integers, a character, a double, and a float:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        char ch = sc.next().charAt(0);
        double d = sc.nextDouble();
        float f = sc.nextFloat();

        double sum = a + b + ch + d + f;
        System.out.println("Sum of the inputs (with type promotion): " + sum);
    }
}
