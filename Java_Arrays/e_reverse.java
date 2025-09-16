package Java_Arrays;

public class e_reverse { 
    public static void reverse(int arr[]){
        int first = 0;
        int last = arr.length-1;

        while(first <= last){
            int temp = arr[last];
            arr[last] = arr[first];
            arr[first] = temp;

            first ++;
            last--;
        }
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8};
        System.out.println("the array is given as:");
        for(int i = 0;i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
        reverse(arr);
        System.err.println("The reverse array is: ");
        for(int i = 0;i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    
}
