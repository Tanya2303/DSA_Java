package Java_Fundamentals;
import java.util.Scanner;
public class sum_of_n_no {
    public static void main(String[] args) {
        System.out.println("Sum of n natural numbers:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of N:");
        int n = sc.nextInt();

        int sum = 0;
        for(int i=1;i<= n;i++){
            sum = sum+i;
        }
        System.out.println("The sum of n numbers are:" + sum);
    }
    
}
