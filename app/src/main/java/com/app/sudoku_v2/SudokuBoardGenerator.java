package com.app.sudoku_v2;

import android.os.Build;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import androidx.annotation.RequiresApi;

public class SudokuBoardGenerator {
    private int[][] board;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SudokuBoardGenerator() {
        board = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        fillDiagonalBox();
        SudokuSolver sudokuSolver = new SudokuSolver(board);
        sudokuSolver.solve();
        board = sudokuSolver.getBoard();
        removeDigits();
    }

    public int[][] getSudoku() {
        return board;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void fillDiagonalBox() {
        for (int i = 0; i < 9; i=i+3) {
            int[] possibleValues=getShuffledArray();
            int index = 0;
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++) {
                    board[i+j][i+k]=possibleValues[index];
                    index++;
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int[] getShuffledArray() {
        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void removeDigits() {
        int digitsToRemove = 25;
        Random rnd = ThreadLocalRandom.current();
        while (digitsToRemove > 0) {
            int row = rnd.nextInt(9);
            int col = rnd.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                    digitsToRemove --;
                }
            }
        }

}
