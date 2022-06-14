package a2122.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solver implements Solver {

    static private int[][] puzzle, newPuzzle, returnPuzzle;
    private static boolean recursive;

    @Override
    public int[][] solve() {
        int row = -1;
        int col = -1;
        boolean empty = true;
        for (int i = 0; i < newPuzzle.length; i++) {
            for (int j = 0; j < newPuzzle.length; j++) {
                if (newPuzzle[i][j] == 0) {
                    row = i;
                    col = j;
                    empty = false;     
                    break;
                }
            }
            if (!empty) {
                break;
            }
        }
        if (empty) {
            recursive = true;
            return newPuzzle;
        }

        for (int k = 1; k <= newPuzzle.length; k++) {
            if (safetyCheck(newPuzzle, row, col, k)) {
                newPuzzle[row][col] = k;
                solve();
                if (recursive) {
                    recursive = true;
                    return newPuzzle;
                } else {
                    newPuzzle[row][col] = 0;
                    recursive = false;
                }
            }
        }
        recursive = false;
        return newPuzzle;
    }

    @Override
    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
        newPuzzle = new int[puzzle.length][puzzle[0].length];
        for (int i = 0; i < newPuzzle.length; i++) {
            newPuzzle[i] = Arrays.copyOf(puzzle[i], puzzle[i].length);
        }
    }

    @Override
    public int[][] getPuzzle() {     
        returnPuzzle = new int[puzzle.length][puzzle[0].length];
        for (int i = 0; i < newPuzzle.length; i++) {
            returnPuzzle[i] = Arrays.copyOf(newPuzzle[i], newPuzzle[i].length);
        }
        return this.returnPuzzle;
    }

    private static boolean safetyCheck(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        int root = (int) Math.sqrt(grid.length);
        int Gridrow = row - row % root;
        int GridCol = col - col % root;

        for (int i = Gridrow; i < Gridrow + root; i++) {
            for (int j = GridCol; j < GridCol + root; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

}
