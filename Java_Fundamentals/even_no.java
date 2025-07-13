package Java_Fundamentals;
import java.util.Scanner;
public class even_no {
    public static void main(String[] args) {
        System.out.println("Even numbers between 9 and 100");
        for(int i=9; i<=100;i++){
            if(i % 2 == 0){
                System.out.print(" " + i + " ");
            }
        }
    }
}
