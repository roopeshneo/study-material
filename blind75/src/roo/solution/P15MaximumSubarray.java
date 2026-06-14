package roo.solution;

import java.util.List;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.

 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */
public class P15MaximumSubarray {

    public int maxSubArray(int[] nums){
        int curSum = 0, maxSoFar = 0;
        for(int n : nums){
            if(curSum < 0) curSum = 0; // do not forget
            curSum += n;
            maxSoFar = Math.max(curSum, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args){
        var maxSubArray = new P15MaximumSubarray();
        var input = List.of(-2,1,-3,4,-1,2,1,-5,4);
        int[] nums = input.stream().mapToInt(Integer::intValue).toArray();
        var actual = maxSubArray.maxSubArray(nums);
        System.out.println("Result: "+actual);
        var expected  = 6;
        assert actual == expected : "Result mismatch";
    }
}
