package roo.solution.maxareaofisland;

import java.util.Arrays;
import java.util.List;

/**
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 */

// frame_size = XXS_bytes(JVM arg 256KB~1MB)/(max_safe_depth ~ m*n)

public class MaxAreaofIsland {

//    in case of diagonal add {-1,-1},{-1,1},{1,-1},{1,1}
    private static final int[][] DIRECTIONS = {{1,0},{-1,0},{0,1},{0,-1}};


    public int maxAreaOfIsland(int[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        int best = 0;

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col] == 1){
                    int currentArea = dfsScanGrid(grid, row, col);
                    best = Math.max(best, currentArea);
                }
            }
        }
        return best;
    }

    private int dfsScanGrid(int[][] grid, int r, int c){
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] != 1){
            return 0;
        }
        // mark visited by resetting to 0
        grid[r][c] = 0;
        int sum = 1;
        for(int[] direction : DIRECTIONS){
            sum += dfsScanGrid(grid, r+direction[0], c+direction[1]);
        }
        return sum;
    }

    public static void main(String[] args){
        var maxAreaofIsland = new MaxAreaofIsland();
        var inputList = List.of(
                List.of(0,0,1,0,0,0,0,1,0,0,0,0,0),
                List.of(0,0,0,0,0,0,0,1,1,1,0,0,0),
                List.of(0,1,1,0,1,0,0,0,0,0,0,0,0),
                List.of(0,1,0,0,1,1,0,0,1,0,1,0,0),
                List.of(0,1,0,0,1,1,0,0,1,1,1,0,0),
                List.of(0,0,0,0,0,0,0,0,0,0,1,0,0),
                List.of(0,0,0,0,0,0,0,1,1,1,0,0,0),
                List.of(0,0,0,0,0,0,0,1,1,0,0,0,0)
        );
        int[][] input = inputList.stream()
                        .map(row -> row.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        int expected = 6;
        int actual = maxAreaofIsland.maxAreaOfIsland(input);
        System.out.println(actual);
        assert actual == expected : "Expected "+expected+" but actual "+actual;
    }
}
