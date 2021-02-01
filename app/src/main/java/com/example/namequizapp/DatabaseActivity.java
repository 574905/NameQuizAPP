package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseActivity extends AppCompatActivity {



    // declaring the variables
    ImageButton deleteButton;
    ListView simpleListView;
    ArrayList<Person> database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        deleteButton = (ImageButton) findViewById(R.id.button_delete);
        database = ((Database) this.getApplication()).getDatabase();


        // finding the ListView in question
        simpleListView = (ListView) findViewById(R.id.simpleListView);

        // Create adapter on database view layout
        DatabaseAdapter databaseAdapter =
                new DatabaseAdapter(this, R.layout.list_view_items, database);

        // Set adapter to the listview
        simpleListView.setAdapter(databaseAdapter);



    }




    }

