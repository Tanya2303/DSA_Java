package Java_Arrays;

public class Array_remove_duplicate {
    public static int remove_duplicate(int num[]){
        int i = 0;
        for(int j=1;j<num.length;j++){
            if(num[i] != num[j]){
                i++;
                num[i] = num[j];
            }
        }
        return i+1;
    }
    public static void main(String[] args) {
        int num[] = {1,1,2,2,3,3,3,3};
        System.out.println("The original array: ");
        for(int i=1;i<num.length;i++){
            System.out.print(num[i] + " ");
        }
        System.out.println();
        int rd = remove_duplicate(num);
       System.out.println("The array after removing duplicate elements is ");
        for (int i = 0; i < rd; i++) {
            System.out.print(num[i] + " ");
        }
    }
}
