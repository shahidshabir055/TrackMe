package com.example.kashapp.Prevalent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kashapp.HomeActivity;
import com.example.kashapp.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;

public class HabitEditorActivity extends AppCompatActivity {
    int habitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_editor);
        EditText editText = findViewById(R.id.editText);

        // Fetch data that is passed from MainActivity
        Intent intent = getIntent();

        // Accessing the data using key and value
        habitId = intent.getIntExtra("habitId", -1);
        if (habitId != -1) {
            editText.setText(HomeActivity.notes.get(habitId));
        } else {

            HomeActivity.notes.add("");
            habitId = HomeActivity.notes.size() - 1;
            HomeActivity.arrayAdapter.notifyDataSetChanged();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // add your code here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HomeActivity.notes.set(habitId, String.valueOf(charSequence));
                HomeActivity.arrayAdapter.notifyDataSetChanged();
                // Creating Object of SharedPreferences to store data in the phone
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(HomeActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // add your code here
            }
        });

}
}
