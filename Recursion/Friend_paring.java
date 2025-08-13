package Recursion;
import java.util.Scanner;

public class Friend_paring {
    public static int FriendPair(int n){
        if(n==1 || n==2){
            return n; // Base case: 1 way to pair 0 or 1 friend
        }
        // Recursive case: either the nth friend is alone or paired with one of the previous friends
        int single = FriendPair(n - 1); // nth friend is alone
        int pair = (n - 1) * FriendPair(n - 2); // nth friend pairs with one of the (n-1) previous friends
        return single + pair; // Total ways = single + pair
    }
    public static void main(String[] args) {
        System.out.print("Enter the number of friends (n): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ways = FriendPair(n);
        System.out.println("Number of ways to pair " + n + " friends is: " + ways);
        sc.close(); 
    }
}
