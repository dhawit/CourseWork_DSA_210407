package Q3;

import java.util.Arrays;

public class MinProductDifference {
    /**
     * Calculates the minimum difference between two subarrays of an integer array such
     * that their product difference is minimum.
     *
     * @param nums the input integer array
     * @return the minimum difference between two subarrays
     */
    public static int findMinProductDifference(int[] nums) {
        Arrays.sort(nums); // sort the input array
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        // calculate the difference between two subarrays with the minimum product difference
        for (int i = 0; i < n / 2; i++) {
            int product1 = nums[i] * nums[n - i - 1];
            int product2 = nums[n / 2 + i] * nums[n - n / 2 - i - 1];
            int diff = Math.abs(product1 - product2);
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 4, 11};
        System.out.println(findMinProductDifference(nums)); // outputs 2
    }
}
