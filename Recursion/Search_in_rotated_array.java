package Recursion;

public class Search_in_rotated_array {
    public static int search(int[] nums, int target) {
        return searchHelper(nums, 0, nums.length - 1, target);
    }

    private static int searchHelper(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1; // Base case: not found
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid; // Found the target
        }

        // Check if left half is sorted
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                return searchHelper(nums, left, mid - 1, target); // Search left half
            } else {
                return searchHelper(nums, mid + 1, right, target); // Search right half
            }
        }
        // Otherwise, right half is sorted
        else {
            if (nums[mid] < target && target <= nums[right]) {
                return searchHelper(nums, mid + 1, right, target); // Search right half
            } else {
                return searchHelper(nums, left, mid - 1, target); // Search left half
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int result = search(nums, target);
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }
    }
}
