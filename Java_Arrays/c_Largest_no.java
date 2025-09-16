package Java_Arrays;


public class c_Largest_no {
    public static int getlargest(int num[]){
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        for(int i=0;i<num.length;i++){
            if(largest < num[i]){
                largest = num[i];
            }
            if(smallest>num[i]){
                smallest = num[i];
            }
        }
        System.out.println("The smallest value in the given array is:" + smallest);
        return largest;
    }
    public static void main(String[] args) {
        int num[] = {1,2,3,4,5,6,7,8,9};
        int lar = getlargest(num);
        System.out.println("The largest value in the given array is : " + lar);

    }
    
}
