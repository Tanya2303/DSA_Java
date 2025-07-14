package Java_Operators;
import java.util.Scanner;
public class logical_op {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter two boolean values:");
        boolean a = sc.nextBoolean();
        boolean b = sc.nextBoolean();

        System.out.println("Logical AND (a && b): " + (a && b));
        System.out.println("Logical OR (a || b): " + (a || b));
        System.out.println("Logical NOT (!a): " + (!a));
        System.out.println("Logical NOT (!b): " + (!b));

        sc.close();
    }
    
}
