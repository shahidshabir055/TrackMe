package com.example.kashapp;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;

public class HomeActivity extends AppCompatActivity {


    public static ArrayList<String> notes = new ArrayList<>();
    public static ArrayList<String> nature = new ArrayList<>();
    public static ArrayList<String> limitTime = new ArrayList<>();
    public static ArrayAdapter arrayAdapter;
    public static  ArrayAdapter arrayAdapter2;
    public static  ArrayAdapter arrayAdapter3;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_habit_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_habit) {
            Intent intent = new Intent(getApplicationContext(), HabitEditorActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView leftListView = findViewById(R.id.leftListView);
        ListView rightListView = findViewById(R.id.rightListView);
        final ListView timeListView = findViewById(R.id.timeListView);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.kashapp", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Habits", null);

        if (set == null) {

        } else {
            notes = new ArrayList(set);
            nature = new ArrayList(set);
            limitTime = new ArrayList(set);
        }

        // Using custom listView Provided by Android Studio
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, notes);
        arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, nature);
        arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, limitTime);
        leftListView.setAdapter(arrayAdapter);
        rightListView.setAdapter(arrayAdapter2);
        timeListView.setAdapter(arrayAdapter3);
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Going from HomeActivity to NotesEditorActivity
                Intent intent = new Intent(getApplicationContext(), HabitEditorActivity.class);
                intent.putExtra("noteId", i);
                startActivity(intent);

            }
        });
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Going from MainActivity to NotesEditorActivity
                Intent intent = new Intent(getApplicationContext(), HabitEditorActivity.class);
                intent.putExtra("natureId", i);
                startActivity(intent);

            }
        });
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Going from MainActivity to NotesEditorActivity
                Intent intent = new Intent(getApplicationContext(), HabitEditorActivity.class);
                intent.putExtra("timeLeftId", i);
                startActivity(intent);

            }
        });
        leftListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                new AlertDialog.Builder(HomeActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                nature.remove(position);
                                arrayAdapter2.notifyDataSetChanged();
                                //timeLimit.remove(position);
                                //arrayAdapter3.notifyDataSetChanged();


                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
        rightListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                new AlertDialog.Builder(HomeActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this Habit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                nature.remove(position);
                                arrayAdapter2.notifyDataSetChanged();

                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
        timeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                new AlertDialog.Builder(HomeActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this Habit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                //limitTime.remove(position);
                                //arrayAdapter3.notifyDataSetChanged();


                            }
                        })

                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }

}
