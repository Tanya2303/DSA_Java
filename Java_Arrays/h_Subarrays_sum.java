package Java_Arrays;

public class h_Subarrays_sum {
    public static void maxSubarrays(int num[]){
        int curr_sum = 0;
        int max_sum = Integer.MIN_VALUE;

        for(int i=0; i<num.length; i++){
            for(int j=i; j<num.length; j++){
                curr_sum = 0;

                for(int k=i;k <= j;k++){
                    curr_sum += num[k];
                }
                System.out.println(curr_sum);
                if(max_sum < curr_sum){
                    max_sum = curr_sum;
                }
            }
        }
        System.out.println("Max sum is: " + max_sum);
    }
    public static void main(String[] args) {
        int num[] = {2,4,6,8,10};
        maxSubarrays(num);
    }
}
