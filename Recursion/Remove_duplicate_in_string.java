package Recursion;

public class Remove_duplicate_in_string {
    
    public static String removeDuplicates(String str) {
        if (str.length() == 0) {
            return ""; // Base case: empty string
        }
        
        char currentChar = str.charAt(0);
        String remainingString = str.substring(1);
        
        // Check if the current character is already in the remaining string
        if (remainingString.indexOf(currentChar) != -1) {
            return removeDuplicates(remainingString); // Skip current character
        } else {
            return currentChar + removeDuplicates(remainingString); // Include current character
        }
    }

    public static void main(String[] args) {
        String input = "aabbccddeeff";
        String result = removeDuplicates(input);
        System.out.println("String after removing duplicates: " + result);
    }
    
}
