package roo.solution;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class P14TopKElement {

    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length < k){
            return new int[]{-1};
        }
        if(nums.length == k) {
            return nums;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for(int n : nums){
            count.put(n, count.getOrDefault(n, 0)+1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(nums.length, Comparator.comparingInt(count::get));
        for(int num: count.keySet()){
            minHeap.add(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        var topK = new P14TopKElement();
        var input = List.of(1,2,1,2,1,2,3,1,3,2);
        int[] nums = input.stream().mapToInt(Integer::intValue).toArray();
        int k = 2;
        var expected = new int[]{1,2};
        var actual = topK.topKFrequent(nums, k);
        System.out.println(Arrays.toString(actual));
        assert Arrays.equals(expected, actual) : "Incorrect response";

    }
}
