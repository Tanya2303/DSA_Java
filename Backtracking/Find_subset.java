package Backtracking;

public class Find_subset {
    
    public static void findSubsets(String str, String ans, int index) {
        // Base case: if index reaches the length of the string, print the answer
        if (index == str.length()) {
            if (ans.isEmpty()) {
                System.out.println("Empty subset");
            } else {
                System.out.println(ans);
            }
            return;
        }

        // Include the current character in the subset
        findSubsets(str, ans + str.charAt(index), index + 1);

        // Exclude the current character from the subset
        findSubsets(str, ans, index + 1);
    }

    public static void main(String[] args) {
        String str = "abc";
        System.out.println("Subsets of the string \"" + str + "\":");
        findSubsets(str, "", 0);
    }
}
