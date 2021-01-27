package com.example.namequizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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



        int i = 0;

        // A simplified for-loop which goes through the HashMap

        for (Map.Entry<Person, Integer> entry : peopleMap.entrySet()) {

            Person key = entry.getKey();

            Log.d("name", key.name);
            Log.d("image", entry.getValue().toString());

            names[i] = key.name;
            images[i] = entry.getValue();
            i++;

        }

        ArrayList< HashMap<String, String>> peopleList = new ArrayList<>();
        for(int indeks = 0; indeks<names.length; indeks++){
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("name",names[indeks]);
            hashMap.put("image",images[indeks]+"");
            peopleList.add(hashMap);

        }

        String[] from={"name","image"};//string array
        int[] to={R.id.textView,R.id.imageView};//int array of views id's

        ListView simpleListView= (ListView)findViewById(R.id.simpleListView);
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,peopleList,R.layout.list_view_items,from,to);//Create object and set the parameters for simpleAdapter
        simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView



    }

    public void addDatabaseItem(Person p, int i) {
        peopleMap.put(p, i);
    }


}