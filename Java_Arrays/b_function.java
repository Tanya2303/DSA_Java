package Java_Arrays;

public class b_function {
        public static void update(int marks[]){
        for(int i=0;i<marks.length;i++){
            marks[i] = marks[i] + 50; // Increment each element by 10
        }
    }
    public static void main(String[] args) {
        int marks[] = {47,45,48,44,46,50};
        System.out.println("Before update:");
        for(int i=0;i<marks.length;i++){
            System.out.print(marks[i] + " ");
        }
        System.out.println();
        update(marks); // Call the update function to modify the array
        System.out.println("After update:");
        for(int i=0;i<marks.length;i++){
            System.out.print(marks[i] + " "); // Print the updated array
        }       
    }
}



