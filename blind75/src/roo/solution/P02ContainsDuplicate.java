package roo.solution;

import roo.utils.DebugLogger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class P02ContainsDuplicate {

    private final DebugLogger log;

    public P02ContainsDuplicate(boolean debug) {
        log = new DebugLogger(debug);
    }

    public P02ContainsDuplicate() {
        this(false);
    }

    // no extra memory but n^2
    public boolean bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                log.log("compare", nums[i] + " :: " + nums[j]);
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // n space and time complexity
    public boolean optimized(int[] nums){
        var seen = new HashSet<Integer>();

        for(int i = 0; i < nums.length; i++ ){
            log.log("index " +i+" seen state is ", seen);
            if(!seen.add(nums[i])) return true;
        }
        return false;
    }


    // Sorting n space and nlogn complexity
    public boolean sorting(int[] nums){

        var sortedArrayCopy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedArrayCopy);
        log.log("sorted array is ", Arrays.toString(sortedArrayCopy));
        // starting index is 1 for i - 1 comparing in sorted
        for(int i = 1; i < sortedArrayCopy.length; i++){
            if(sortedArrayCopy[i] == sortedArrayCopy[i-1]){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // more than one adds to 8
        var input = List.of(2, 3, -1, 0, 9, 4, 5, -1, 5);
        int[] nums = input.stream().mapToInt(Integer::intValue).toArray();
        var target = 8;
        var p02ContainsDuplicate = new P02ContainsDuplicate();
        long startTime = System.currentTimeMillis();
        var bruteResult = p02ContainsDuplicate.bruteForce(nums);
        System.out.println("Run with bruteforce : " + bruteResult);
        var bruteforceRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Brute force runtime : " + bruteforceRuntime);
        startTime = System.currentTimeMillis();
        var optimizedResult = p02ContainsDuplicate.optimized(nums);
        System.out.println("Run with optimized : " + optimizedResult);
        var optimizedRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Optimzed runtime : " + optimizedRuntime);
        startTime = System.currentTimeMillis();
        var sortingResult = p02ContainsDuplicate.sorting(nums);
        System.out.println("Run with optimized : " + sortingResult);
        var sortingRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Sorting runtime : " + sortingRuntime);

    }

}
