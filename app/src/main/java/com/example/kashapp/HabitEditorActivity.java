package com.example.kashapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;

public class HabitEditorActivity extends AppCompatActivity {

    int noteID;
    int natureID;
    int timeLeftID;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_editor);
        submit = (Button) findViewById(R.id.submit);
        final EditText timeLeft= (EditText) findViewById(R.id.time);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cnt = timeLeft.getText().toString();
                int count = Integer.parseInt(cnt);
                count=count * 86400000;
                new CountDownTimer(count, 100) {
                    public void onTick(long millisUntilFinished) {
                        //timeLeft.setText(String.valueOf(counter));
                        //counter++;
                        NumberFormat f = new DecimalFormat("00");
                        long days = (millisUntilFinished/(60*60*24*1000));
                        long hour = (millisUntilFinished / 3600000) % 24;
                        long min = (millisUntilFinished / 60000) % 60;
                        long sec = (millisUntilFinished / 1000) % 60;
                        timeLeft.setText(f.format(days)+":"+ f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                    }

                    public void onFinish() {
                        timeLeft.setText("00:00:00");
                    }
                }.start();

                Intent intent = new Intent(HabitEditorActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText1 = (EditText) findViewById(R.id.description);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);
        natureID = intent.getIntExtra("natureID", -1);
        timeLeftID =intent.getIntExtra("timeLeftId",-1);

        if (noteID != -1) {
            editText.setText(HomeActivity.notes.get(noteID));
        }
        else {
            HomeActivity.notes.add("");                // as initially, the note is empty
            noteID = HomeActivity.notes.size() - 1;
            HomeActivity.arrayAdapter.notifyDataSetChanged();
        }
        if (natureID != -1) {
            editText1.setText(HomeActivity.nature.get(natureID));
        } else {
            HomeActivity.nature.add("");                // as initially, the note is empty
            natureID = HomeActivity.nature.size() - 1;
            HomeActivity.arrayAdapter2.notifyDataSetChanged();
        }
        if (timeLeftID != -1) {
            timeLeft.setText(HomeActivity.limitTime.get(timeLeftID));
        } else {
            HomeActivity.limitTime.add("");                // as initially, the note is empty
            timeLeftID = HomeActivity.limitTime.size() - 1;
            HomeActivity.arrayAdapter3.notifyDataSetChanged();
        }


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HomeActivity.notes.set(noteID, String.valueOf(s));
                HomeActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.kashapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(HomeActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HomeActivity.nature.set(natureID, String.valueOf(s));
                HomeActivity.arrayAdapter2.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.kashapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(HomeActivity.nature);
                sharedPreferences.edit().putStringSet("nature", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        timeLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                HomeActivity.limitTime.set(timeLeftID, String.valueOf(s));
                HomeActivity.arrayAdapter3.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.kashapp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(HomeActivity.limitTime);
                sharedPreferences.edit().putStringSet("timeLimit", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }
}
