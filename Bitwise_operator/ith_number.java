package Bitwise_operator;

public class ith_number {
    public static void main(String[] args) {
        int n = 5; // Example number
        int i = 2; // Example position (0-based index)
        int bitmask = 1 << i; // Create a bitmask by left-shifting 1 by i positions
        int ithBit = (n & bitmask) > 0 ? 1 : 0; // Use AND operation to isolate the ith bit
        System.out.println("The " + i + "th bit of " + n + " is: " + ithBit);
    }
    
}
