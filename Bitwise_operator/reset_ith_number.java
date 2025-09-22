package Bitwise_operator;

public class reset_ith_number {
    public static void main(String[] args) {
        int n = 5; // Example number
        int i = 3; // Example position (0-based index)
        int bitmask = ~(1 << i); // Create a bitmask by left-shifting 1 by i positions and then negating it
        int newNumber = n & bitmask; // Use AND operation to reset the ith bit
        System.out.println("Original number: " + n);
        System.out.println("New number after resetting the " + i + "th bit: " + newNumber);
    }
    
}
