package Java_Arrays;
import java.util.Scanner;
public class Array_rotate_by_k {
    public static void reverse(int arr[],int s,int e){
        int sp = s;
        int ep = e;
        while(s <= k){
            int temp = arr[sp];
            arr[sp] = arr[ep];
            arr[ep] = temp;
            sp++;
            ep--;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The original array");
        int arr[] = {3,-2,1,4,6,9,8};
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
        System.err.print("Enter the value of rotation:");
        int k = sc.nextInt();
        int n = 7;
        k = k % n;
        reverse(arr,0,n-1);
        reverse(arr, 0, k-1);
        reverse(arr, k, n-1);
        
    }
}
