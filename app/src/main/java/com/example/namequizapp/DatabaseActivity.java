package com.example.namequizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    HashMap<Person, Integer> peopleMap = new HashMap<>();
    Person p1 = new Person("Bendik");
    Person p2 = new Person("Morten");
    Person p3 = new Person("Thomas");


    String [] names  = new String[3];
    int [] images = new int[3];

    void prepareMenu() {
        addDatabaseItem(p1, R.drawable.bendik);
        addDatabaseItem(p2, R.drawable.morten);
        addDatabaseItem(p3, R.drawable.thomas);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        prepareMenu();


        // A simplified for-loop which goes through the HashMap
        // makes two new arrays containing all the names and images separately
        int i = 0;
        for (Map.Entry<Person, Integer> entry : peopleMap.entrySet()) {

            Person key = entry.getKey();

            Log.d("name", key.name);
            Log.d("image", entry.getValue().toString());

            names[i] = key.name;
            images[i] = entry.getValue();
            i++;

        }

        // Making a list containing the HashMap of the two arrays
        ArrayList<HashMap<String, String>> peopleList = new ArrayList<>();
        for (int index = 0; index < names.length; index++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", names[index]);
            hashMap.put("image", images[index] + "");
            peopleList.add(hashMap);

        }

        // Making two new arrays, so that the SimpleAdapter can find the right elements
        String[] from = {"name", "image"}; //string array
        int[] to = {R.id.textView, R.id.imageView}; //int array of views id's

        // Finding the ListView in question
        ListView simpleListView = (ListView) findViewById(R.id.simpleListView);

        //Create object and set the parameters for simpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, peopleList, R.layout.list_view_items, from, to);

        //Sets the adapter for listView
        simpleListView.setAdapter(simpleAdapter);
        ImageButton deleteButton = (ImageButton) findViewById(R.id.button_delete);

        //Need to make a listener when the button is clicked and a person should be deleted





    }


    public void addDatabaseItem(Person p, int i) {
        peopleMap.put(p, i);
    }

    public void deletePerson(Person p) {

        String name = p.getName();
        for (Map.Entry<Person, Integer> entry : peopleMap.entrySet()) {
            Person key = entry.getKey();
            if(name.equals(key.name)){
                peopleMap.remove(key);
                peopleMap.remove(entry.getValue());
            }
        }
    }


    }

