package Java_Arrays;

public class Subarrays {
    public static void Subarrays(int num[]){
        int total_sa = 0;
        for(int i=0; i<num.length; i++){
            for(int j=i; j<num.length; j++){
                for(int k=i;k <= j;k++){
                    System.out.print(num[k] + " ");
                }
                total_sa++;
                System.out.println(" ");
            }
        }
        System.out.println("Total number of subarrays: " + total_sa);
    }
    public static void main(String[] args) {
        int num[] = {2,4,6,8,10};
        Subarrays(num);
    }
}
