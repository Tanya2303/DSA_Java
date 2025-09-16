package Java_Arrays;

public class f_pairs {
    public static void pairs(int num[]){
        int totalpair = 0;
        for(int i = 0; i<num.length; i++){
            int curr = num[i];
            for(int j=i+1; j <num.length; j++){
                System.out.print("(" + curr + "," + num[j] + ")" );
                totalpair++;
            }
            System.out.println(" ");
        }
        System.out.println("Total number of pairs: " + totalpair);
    }
    public static void main(String[] args) {
        int num[] = {2,4,6,8,10};
        pairs(num);
        
    }
}
