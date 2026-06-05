package roo.solution;

import roo.utils.DebugLogger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class P01TwoSum {
    private final DebugLogger log;

    public P01TwoSum(boolean debug) {
        log = new DebugLogger(debug);
    }

    public P01TwoSum() {
        this(false);
    }

    /**
     * Iterate through nums and update state after each pass
     * @param nums - input array
     * @param target - value
     * @return int[]
     */
    public int[] bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            log.log("index", i);
            for (int j = i + 1; j < nums.length; j++) {
                log.log("current pair", nums[i] + " + " + nums[j]);
                if (nums[i] + nums[j] == target) return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }

    // Wont work with duplicate num[i] as map will replace value
    public int[] optimized(int[] nums, int target) {
        var visitedIndex = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            log.log("need", need);
            if (visitedIndex.containsKey(need)) {
                return new int[] {visitedIndex.get(need), i};
            }
            visitedIndex.put(nums[i], i);
            log.log("map", visitedIndex);
        }
        return new int[] {-1, -1};
    }

    // pitfall duplicate element and multiple solution possible
    public static void main(String[] args) {
        // more than one adds to 8
        var input = List.of(2, 3, -1, 0, 9, 4, 5);
        int[] nums = input.stream().mapToInt(i -> i).toArray();
        var target = 8;
        var p01TwoSum = new P01TwoSum();
        long startTime = System.currentTimeMillis();
        var bruteResult = Arrays.toString(p01TwoSum.bruteForce(nums, target));
        System.out.println("Run with bruteforce : " + bruteResult);
        var bruteforceRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Brute force runtime : " + bruteforceRuntime);
        startTime = System.currentTimeMillis();
        var optimizedResult = Arrays.toString(p01TwoSum.optimized(nums, target));
        System.out.println("Run with optimized : " + optimizedResult);
        var optimizedRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Optimzed runtime : " + optimizedRuntime);
        if (bruteResult.equals(optimizedResult)) {
            System.out.println("Mismatch" + bruteResult + " :: " + optimizedResult);
        }
    }
}
