package com.example.kashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.BufferUnderflowException;

public class feature extends AppCompatActivity {
    private Button ViewHabitsBtn;
    private Button RecommendationBtn;
    private Button ExpensesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        ViewHabitsBtn = (Button) findViewById(R.id.ViewHabits);
        RecommendationBtn = (Button) findViewById(R.id.RecommendationBtn);
        ExpensesBtn = (Button) findViewById(R.id.shopItems);
        ViewHabitsBtn.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View view){
                Intent intent = new Intent(feature.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        RecommendationBtn.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View view){
                Intent intent = new Intent(feature.this,ToDoMainActivity.class);
                startActivity(intent);
            }
        });
        ExpensesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(feature.this,ExpenseMainActivity.class);
                startActivity(intent);
            }
        });


    }
}
