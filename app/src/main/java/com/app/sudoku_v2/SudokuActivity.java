package com.app.sudoku_v2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SudokuActivity extends AppCompatActivity implements View.OnClickListener {

    private final Sudoku sudoku = Sudoku.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonSolve = findViewById(R.id.buttonSolve);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonHint = findViewById(R.id.buttonHint);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonSolve.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonHint.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                sudoku.setSelectedCellValue(1);
                break;
            case R.id.button2:
                sudoku.setSelectedCellValue(2);
                break;
            case R.id.button3:
                sudoku.setSelectedCellValue(3);
                break;
            case R.id.button4:
                sudoku.setSelectedCellValue(4);
                break;
            case R.id.button5:
                sudoku.setSelectedCellValue(5);
                break;
            case R.id.button6:
                sudoku.setSelectedCellValue(6);
                break;
            case R.id.button7:
                sudoku.setSelectedCellValue(7);
                break;
            case R.id.button8:
                sudoku.setSelectedCellValue(8);
                break;
            case R.id.button9:
                sudoku.setSelectedCellValue(9);
                break;
            case R.id.buttonHint:
                if (!sudoku.getHint()){
                    Context context = getBaseContext();
                    Toast.makeText(context, "Invalid values in cells", Toast.LENGTH_LONG).show();}
                break;
            case R.id.buttonSolve:
                if (!sudoku.solve()){
                    Context context = getBaseContext();
                    Toast.makeText(context, "Sudoku can't be solved", Toast.LENGTH_LONG).show();}
                break;
            case R.id.buttonDelete:
                sudoku.setSelectedCellValue(0);
                break;
        }
        findViewById(R.id.sudokuBoardView).invalidate();
    }
}
