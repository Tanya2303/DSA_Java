package Java_Fundamentals;
import java.util.Scanner;
public class max_of_3_no {
    public static void main(String[] args) {
        System.out.println("The max of 3 numbers:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number 1:");
        int a = sc.nextInt();
        System.out.print("Enter the number 2:");
        int b = sc.nextInt();
        System.out.print("Enter the number 3:");
        int c = sc.nextInt();

        if(a>b && b>c){
            System.out.print(a + " " + "is the maximum number among the given three number");
        }
        else if(b>a && b>c){
            System.out.print(b + " " + "is the maximum number among the given three number");
        }
        else{
            System.out.print(c + " " + "is the maximum number among the given three number");
        }

    }
    
}
