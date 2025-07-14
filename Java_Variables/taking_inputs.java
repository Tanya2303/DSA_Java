import java.util.Scanner;

public class taking_inputs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter values for boolean, string, integer, double, character, float, long, short, and byte:");
        boolean bl = sc.nextBoolean();
        sc.nextLine();
        String st = sc.nextLine();
        int i = sc.nextInt();
        double d = sc.nextDouble();
        char ch = sc.next().charAt(0);
        float f = sc.nextFloat();
        long l = sc.nextLong();
        short sh = sc.nextShort();
        byte b = sc.nextByte();

        System.out.println("You entered:");
        System.out.println("Boolean: " + bl);
        System.out.println("String: " + st);
        System.out.println("Integer: " + i);
        System.out.println("Double: " + d);
        System.out.println("Character: " + ch);
        System.out.println("Float: " + f);
        System.out.println("Long: " + l);
        System.out.println("Short: " + sh);
        System.out.println("Byte: " + b);
        sc.close();
    }
}
