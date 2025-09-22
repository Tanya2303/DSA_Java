package Bitwise_operator;

public class set_ith_bit {
    public static void main(String[] args) {
        int n = 5; // Example number
        int i = 3; // Example position (0-based index)
        int bitmask = 1 << i; // Create a bitmask by left-shifting 1 by i positions
        int newNumber = n | bitmask; // Use OR operation to set the ith bit
        System.out.println("Original number: " + n);
        System.out.println("New number after setting the " + i + "th bit: " + newNumber);
    }
    
}
