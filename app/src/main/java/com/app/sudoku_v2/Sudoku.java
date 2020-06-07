package com.app.sudoku_v2;

import android.os.Build;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import androidx.annotation.RequiresApi;

public class Sudoku {

    private static Sudoku instance;
    private int[][] board;
    private final int EMPTY = 0;
    private final int SIZE = 9;
    private int selectedRow = -1;
    private int selectedCol = -1;

    private Sudoku() {
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
    }

    public static Sudoku getInstance() {
        if (instance == null) {
            instance = new Sudoku();
        }
        return instance;
    }

    public void setClearBoard() {
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
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setRandomBoard() {
        SudokuBoardGenerator sudokuBoardGenerator = new SudokuBoardGenerator();
        board = sudokuBoardGenerator.getSudoku();
    }

    public void setSelectedCell(int x, int y) {
        selectedCol = y;
        selectedRow = x;
    }

    public void setSelectedCellValue(int value) {
        if (value == 0 || isOk(selectedRow, selectedCol, value)) {
        board[selectedRow][selectedCol] = value;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean getHint() {
        SudokuSolver sudokuSolver = new SudokuSolver(copyBoard());
        if (sudokuSolver.solve()) {
            int[][] solvedBoard = sudokuSolver.getBoard().clone();
            Random rnd = ThreadLocalRandom.current();
            int i = rnd.nextInt(81);
            int r;
            int c;
            for (int j = 0; j < 81; j++) {
                c = (i + j) % 9;
                r = ((i + j) % 81) / 9;
                if (board[r][c] == 0) {
                    board[r][c] = solvedBoard[r][c];
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    private int[][] copyBoard() {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }

    public boolean solve() {
        SudokuSolver sudokuSolver = new SudokuSolver(board);
        if (sudokuSolver.solve()) {
            board = sudokuSolver.getBoard();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRow(int row, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;
        return false;
    }

    private boolean checkCol(int col, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;
        return false;
    }

    private boolean checkBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;
        return false;
    }

    public boolean isOk(int row, int col, int number) {
        return !checkRow(row, number) && !checkCol(col, number) && !checkBox(row, col, number);
    }


}
