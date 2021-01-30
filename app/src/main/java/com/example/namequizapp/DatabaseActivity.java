package com.example.namequizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseActivity extends AppCompatActivity {



    // declaring the variables
    String [] names = new String[3];
    int [] images = new int[3];
    ImageButton deleteButton;
    ListView simpleListView;
    ArrayList<HashMap<String, String>> peopleList = new ArrayList<>();
    ArrayList<Person> database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        deleteButton = (ImageButton) findViewById(R.id.button_delete);
        database = ((Database) this.getApplication()).getDatabase();
        int hei = getResources().getIdentifier("thomas", "drawable", getPackageName());


        // a simplified for-loop which goes through the ArrayList
        // makes two new arrays containing all the names and images separately
        int i = 0;
        for (Person p : database) {

            names[i] = p.getName();
            images[i] = getResources().getIdentifier(p.getName().toLowerCase(), "drawable", getPackageName());
            i++;

        }

        // making a list containing the HashMap of the two arrays

        for (int index = 0; index < names.length; index++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", names[index]);
            hashMap.put("image", images[index] + "");
            peopleList.add(hashMap);

        }

        // making two new arrays, so that the SimpleAdapter can find the right elements
        String[] from = {"name", "image"}; //string array
        int[] to = {R.id.textView, R.id.imageView}; //int array of views id's

        // finding the ListView in question
        simpleListView = (ListView) findViewById(R.id.simpleListView);

        // create object and set the parameters for simpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, peopleList, R.layout.list_view_items, from, to);

        // sets the adapter for listView
        simpleListView.setAdapter(simpleAdapter);



    }




    }

