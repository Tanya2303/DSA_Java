package Java_Arrays;

public class Array_2nd_largest {
    public static int secondlargest(int num[]){
        int lar = num[0];
        int secondlar = -1;
        for(int i=1;i<num.length;i++){
            if(num[i]>lar){
                secondlar = lar;
                lar = num[i];
            } else if(num[i]<lar && num[i]>secondlar){
                secondlar = num[i];
            }
        }
        if(secondlar == -1 || secondlar == lar){
            boolean allsame = true;
            for(int n : num){
                if(n != lar){
                    allsame = false;
                    break;
                }
            }
            return allsame ? -1 : secondlar;
        }
        return secondlar;
    }
    public static void main(String[] args) {
        int num[] = {5,6,2,3,4,8};
        for(int i=0;i<num.length;i++){
            System.out.print(num[i] + " ");
        }
        System.out.println();
        int seclar = secondlargest(num);
        System.out.println("The second largest element in the array is: " + seclar);
    }
}
