package Java_Fundamentals;
import java.util.Scanner;

public class java_prime {
    public static void main(String[] args) {
        System.out.println("Identifying prime and non prime number");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number:");
        int num = sc.nextInt();
        boolean isPrime = true;

        for(int i = 2; i < num; i++){
            if(num % i == 0){
                isPrime = false;
            }
        }
        if(isPrime == false){
            System.out.println("The number is not prime");
        }
        else{
            System.out.println("The number is prime");
        }
    }
}
