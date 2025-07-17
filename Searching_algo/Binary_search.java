package Searching_algo;
import java.util.*;
public class Binary_search {
    public static int binary_search(int num[],int key){
        int start = 0;
        int end = num.length-1;
        
        while(start <= end){
            int mid = (start + end) / 2;
            if(num[mid] == key){
                return mid;
            }
            if(num[mid] < key){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num[] = {1,2,3,4,5,6,7,8,9};
        System.out.print("Enter the value of key: ");
        int key = sc.nextInt();
        int index = binary_search(num, key);
        System.out.println("The key is at index : " + index);

    }
}
