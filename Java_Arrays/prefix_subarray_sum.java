package Java_Arrays;

public class prefix_subarray_sum {
    public static void maxSubarrays(int num[]) {
        int curr_sum = 0; // Current subarray sum
        int max_sum = Integer.MIN_VALUE; // Maximum subarray sum
        // Prefix sum array to store cumulative sums
        // prefix_arr[i] will hold the sum of elements from index 0 to i
        int prefix_arr[] = new int[num.length]; // Initialize prefix sum array

        // Calculate prefix sum array
        prefix_arr[0] = num[0]; // First element is the same
        for (int i = 1; i < prefix_arr.length; i++) { // Start from the second element
            // Each prefix sum is the sum of the previous prefix sum and the current element
            prefix_arr[i] = prefix_arr[i - 1] + num[i]; // Cumulative sum
        }

        // Calculate max subarray sum using prefix sums
        for (int i = 0; i < num.length; i++) {
            for (int j = i; j < num.length; j++) {
                // Calculate the current subarray sum using the prefix sum array
                // If i is 0, the sum is just prefix_arr[j]
                // Otherwise, subtract the prefix sum up to i-1 from prefix_arr[j]
                curr_sum = (i == 0) ? prefix_arr[j] : prefix_arr[j] - prefix_arr[i - 1]; 

                System.out.println("Subarray sum from " + i + " to " + j + " = " + curr_sum);
                // Update max_sum if the current subarray sum is greater
                if (curr_sum > max_sum) {
                    max_sum = curr_sum;
                }
            }
        }

        System.out.println("Max sum is: " + max_sum);
    }

    public static void main(String[] args) {
        int num[] = {2, 4, 6, 8, 10};
        maxSubarrays(num);
    }
}
