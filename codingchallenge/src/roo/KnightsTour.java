package roo;

/**
 * A knight's tour is a sequence of moves by a knight on a chessboard
 * such that all squares are visited once.
 */
public class KnightsTour {

    //The problem is essentially finding all Hamiltonian paths on a graph
    // where the vertices are the board squares and the edges are valid knight moves.

    // All 8 possible moves for a knight
    /*
    Index (i)	Row Offset (ROW_MOVES[i])	Column Offset (COL_MOVES[i])	Resulting Move Direction
        0	            -2	                        +1	                        2 Up, 1 Right
        1	            -1	                        +2	                        1 Up, 2 Right
        2	            +1	                        +2	                        1 Down, 2 Right
        3	            +2	                        +1	                        2 Down, 1 Right
        4	            +2	                        -1	                        2 Down, 1 Left
        5	            +1	                        -2	                        1 Down, 2 Left
        6	            -1	                        -2	                        1 Up, 2 Left
        7	            -2	                        -1	                        2 Up, 1 Left
     */
    private static final int[] ROW_MOVES = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    public static long countTotalTours(int n){

        // A board smaller than 1x1 has no squares; 1x1 has exactly 1 tour (start and finish).
        // 2x2 and 3x3 are mathematically impossible to have a full tour.
        if (n <= 0) return 0;
        if (n == 1) return 1; // confirm with interviewer if this needs to be 0 or 1
        if (n < 4) return 0;

        long totalTours = 0;

        //
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                // Map (row, col) to a unique bit position: (row * n) + col
                int startBit = (row * n) + col;
                long visited = 1L << startBit; // instead of managing visited array and cool hack of using bitmask
                totalTours += backtrack(row, col, 1, visited, n);
            }
        }

        return totalTours;
    }

    public static long backtrack(int r, int c, int count, long visited, int n){
        // Base Case: If we have visited all N * N squares, we found a valid tour.
        if (count == n * n) {
            return 1;
        }

        long toursFound = 0;

        // Explore all 8 potential knight moves at any given point
        for (int i = 0; i < 8; i++ ){
            int nextRow = r + ROW_MOVES[i];
            int nextCol = c + COL_MOVES[i];
            // 1. Boundary Check
            if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                int nextBit = (nextRow * n) + nextCol;
                // 2. Visited Check using Bitwise AND
                if ((visited & (1L << nextBit)) == 0) {
                    // Toggle bit ON
                    visited |= (1L << nextBit);
                    toursFound += backtrack(nextRow, nextCol, count + 1, visited, n);
                    // Toggle bit OFF (Backtrack) using Bitwise XOR
                    visited ^= (1L << nextBit);
                }
            }
        }
        return toursFound;
    }

    public static void main(String[] args){
        int n = 5; // Change N to test different board sizes
        System.out.println("Total Knight's Tours for " + n + "x" + n + ": " + countTotalTours(n));
    }

}
