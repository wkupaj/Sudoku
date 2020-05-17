package com.app.sudoku_v2;

public class Sudoku {

    private static Sudoku instance;
    private int[][] board;
    public static final int EMPTY = 0;
    public static final int SIZE = 9;
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

    public void setSelectedCell(int x, int y) {
        selectedCol = y;
        selectedRow = x;
    }

    public void setSelectedCellValue(int value) {
        if (value == 0 || isOk(selectedRow, selectedCol, value))
            board[selectedRow][selectedCol] = value;

    }

    public int[][] getBoard() {
        return board;
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

    private boolean isOk(int row, int col, int number) {
        return !checkRow(row, number) && !checkCol(col, number) && !checkBox(row, col, number);
    }

    public boolean solve() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isOk(r, c, num)) {
                            board[r][c] = num;
                            if (solve()) return true;
                            else board[r][c] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
