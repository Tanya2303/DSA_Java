package Bitwise_operator;

public class FindUnique {
    public static void main(String[] args) {
        int[] arr = {2,3,1,6,3,6,2};
        int unique = 0;
        for(int n : arr){
            unique ^= n; // XOR operation
        }
        System.out.println("The unique element in the array is: " + unique);
    }
}
