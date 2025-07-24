package Java_Arrays;

public class Array_sorted_rotated {
    public static boolean check(int num[]){
        int count = 0;
        for(int i=1;i<num.length;i++){
            if(num[i] > num[(i+1) % num.length]){
                count++;
            }
        }
        if(count <= 1){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int num[] = {4,5,6,7,1,2,3};
        System.out.println("The original array:");
        for(int i=1;i<num.length;i++){
            System.out.print(num[i]);
        }
        System.out.println();
        boolean ch = check(num);
        System.out.println("The given array is: " + ch);
    }
    
}
