package Java_Arrays;

public class trapping_water {
    public static int trappedWater(int height[]){
        int n = height.length; // Length of the height array
        int leftMax[] = new int[n];// Array to store the maximum height to the left of each index
        int rightMax[] = new int[n];// Array to store the maximum height to the right of each index
// Initialize the first element of leftMax and the last element of rightMax
        leftMax[0] = height[0];// First element is the same as height
        for(int i=1; i<n; i++){// Start from the second element
            leftMax[i] = Math.max(height[i], leftMax[i-1]); // Each left max is the maximum of the current height and the previous left max
        }
// Initialize the last element of rightMax
        rightMax[n-1] = height[n-1];// Last element is the same as height
        for(int i=n-2; i>=0; i--){// Start from the second last element
            rightMax[i] = Math.max(height[i], rightMax[i+1]);// Each right max is the maximum of the current height and the next right max
        }
// Calculate the total trapped water
        int trappedWater = 0;// Initialize total trapped water to zero
        for(int i=0; i<n; i++){// Iterate through each index
            int waterLevel = Math.min(leftMax[i], rightMax[i]);// Water level at the current index is the minimum of left max and right max
            trappedWater += waterLevel - height[i];// Add the trapped water at the current index to the total
        }
        return trappedWater; // Return the total trapped water
    }
    public static void main(String[] args) {
        int height[] = {4,2,0,6,3,2,5};
        System.out.println("Total trapped water: " + trappedWater(height));
    }
    
}