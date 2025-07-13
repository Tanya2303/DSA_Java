package Java_Fundamentals;
import java.util.Scanner;
public class area_of_circle {
    public static void main(String[] args) {
        System.out.println("Calculating area of circle");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the radius of circle: ");
        double r = sc.nextDouble();

        double area = 3.14 * r * r;
        System.out.println("The area of circle is: " + area);

    
    }
    
}
