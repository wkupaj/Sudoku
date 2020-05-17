package com.app.sudoku_v2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoardView extends View {

    private Paint solidLinePaint;
    private Paint thinLinePaint;
    private Paint selectedCellPaint;
    private Paint otherCellPaint;
    private Paint textPaint;
    private int size = 9;
    private int sectorSize = 3;
    private float cellSize = 0;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private final Sudoku sudoku = Sudoku.getInstance();

    public SudokuBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        solidLinePaint = new Paint();
        solidLinePaint.setColor(Color.BLACK);
        solidLinePaint.setStrokeWidth(4F);
        solidLinePaint.setStyle(Paint.Style.STROKE);
        thinLinePaint = new Paint();
        thinLinePaint.setColor(Color.BLUE);
        thinLinePaint.setStrokeWidth(2F);
        thinLinePaint.setStyle(Paint.Style.STROKE);
        selectedCellPaint = new Paint();
        selectedCellPaint.setColor(Color.GREEN);
        selectedCellPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        otherCellPaint = new Paint();
        otherCellPaint.setColor(Color.GRAY);
        otherCellPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        cellSize = (float) getWidth() / size;
        textPaint.setTextSize(cellSize / 1.5F);
        fillChecked(canvas);
        canvas.drawRect(0F, 0F, getWidth(), getHeight(), solidLinePaint);
        Paint tmpPaint;
        for (int i = 1; i < size; i++) {
            if (i % 3 == 0) tmpPaint = solidLinePaint;
            else tmpPaint = thinLinePaint;
            canvas.drawLine(i * cellSize, 0F, i * cellSize, getHeight(), tmpPaint);
            canvas.drawLine(0F, i * cellSize, getWidth(), i * cellSize, tmpPaint);
        }

    }

    private void fillChecked(Canvas canvas) {
        if (selectedCol == -1 || selectedRow == -1) return;
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++) {
                if (selectedRow == r && selectedCol == c) {
                    canvas.drawRect(c * cellSize, r * cellSize, (c + 1) * cellSize, (r + 1) * cellSize, selectedCellPaint);
                } else if (selectedRow == r || selectedCol == c) {
                    canvas.drawRect(c * cellSize, r * cellSize, (c + 1) * cellSize, (r + 1) * cellSize, otherCellPaint);
                } else if (r / sectorSize == selectedRow / sectorSize && c / sectorSize == selectedCol / sectorSize) {
                    canvas.drawRect(c * cellSize, r * cellSize, (c + 1) * cellSize, (r + 1) * cellSize, otherCellPaint);
                }
            }
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        int[][] board = sudoku.getBoard();
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++) {
                if(board[r][c] != 0) {
                    String num = Integer.toString(board[r][c]);
                    Rect bound = new Rect();
                    textPaint.getTextBounds(num, 0, 1, bound);
                    float textWidth = textPaint.measureText(num);
                    float textHeight = bound.height();
                    canvas.drawText(num, (c * cellSize) + cellSize / 2 - textWidth / 2, (r * cellSize) + cellSize / 2 + textHeight / 2, textPaint);
                }
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            selectedRow = (int) (event.getY() / cellSize);
            selectedCol = (int) (event.getX() / cellSize);
            sudoku.setSelectedCell(selectedRow, selectedCol);
            invalidate();
            return true;
        } else return false;
    }
}
