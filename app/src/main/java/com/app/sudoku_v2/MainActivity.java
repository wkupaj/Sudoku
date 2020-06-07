package com.app.sudoku_v2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Sudoku sudoku = Sudoku.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sudoku.setClearBoard();
                openSudokuActivity();
            }
        });

        Button buttonRandStart = findViewById(R.id.buttonRandStart);
        buttonRandStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                sudoku.setRandomBoard();
                openSudokuActivity();
            }
        });
    }

    public void openSudokuActivity(){
        Intent intent = new Intent(this, SudokuActivity.class);
        startActivity(intent);
    }


}
