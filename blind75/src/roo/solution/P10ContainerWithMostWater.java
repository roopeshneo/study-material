package roo.solution;

import roo.utils.DebugLogger;

import java.util.List;

public class P10ContainerWithMostWater {

    private final DebugLogger log;

    public P10ContainerWithMostWater(boolean debug) {
        log = new DebugLogger(debug);
    }

    public P10ContainerWithMostWater() {
        this(false);
    }

    public int bruteForce(int[] heights) {
        if (heights == null || heights.length < 2) return 0;

        int best = 0;

        for (int left = 0; left < heights.length; left++) {
            for (int right = left + 1; right < heights.length; right++) {
                int area = (right - left) * Math.min(heights[left], heights[right]);
                best = Math.max(best, area);
                log.log("area / best", area + " / " + best);
            }
        }
        return best;
    }

    public int optimized(int[] heights) {
        if (heights.length <= 1) return 0;
        int left = 0, right = heights.length - 1, best = 0;

        while (left < right) {
            int area = (right - left) * Math.min(heights[left], heights[right]);
            best = Math.max(best, area);
            log.log("Left / Right / Area / Best", left + " / " + right + " / " + area + " / " + best);
            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        var input = List.of(2, 3, 1, 0, 9, 4, 5, 0, 5);
        int[] nums = input.stream().mapToInt(i -> i).toArray();
        var p10ContainerWithMostWater = new P10ContainerWithMostWater();
        var startTime = System.currentTimeMillis();
        var bruteResult = p10ContainerWithMostWater.bruteForce(nums);
        System.out.println("Run with bruteforce : " + bruteResult);
        var bruteforceRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Brute force runtime : " + bruteforceRuntime);
        startTime = System.currentTimeMillis();
        var optimizedResult = p10ContainerWithMostWater.optimized(nums);
        System.out.println("Run with optimized : " + optimizedResult);
        var optimizedRuntime = System.currentTimeMillis() - startTime;
        System.out.println("Optimized runtime : " + optimizedRuntime);
    }

}
