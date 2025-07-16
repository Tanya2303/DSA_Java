package Searching_algo;
import java.util.*;
public class Linear_search {
    public static int linear_search (int num[],int key){
        for(int i=0;i<num.length;i++){
            if(num[i]==key){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int num[] = {1,2,3,4,5,6,7,8,9};
        int key = 7;
        int index = linear_search(num,key);
        if(index == -1){
            System.out.println("Key not found");
        } else{
            System.out.println("Key is at index: " + index);
        }
    }
    
}
