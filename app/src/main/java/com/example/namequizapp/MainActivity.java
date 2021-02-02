package com.example.namequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.namequizapp.Person;
import com.example.namequizapp.Database;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected Map<String, Object> actions = new HashMap<>();


    void prepareMenu() {
        addMenuItem("Quiz", QuizActivity.class);
        addMenuItem("Database", DatabaseActivity.class);
        addMenuItem("Add new person", AddActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person p1 = new Person("Bendik", getDrawable(R.drawable.bendik));
        Person p2 = new Person("Morten", getDrawable(R.drawable.morten));
        Person p3 = new Person("Thomas", getDrawable(R.drawable.thomas));

        if(!((Database) this.getApplication()).existsAlready(p1)){
            ((Database) this.getApplication()).addPerson(p1);
        }

        if(!((Database) this.getApplication()).existsAlready(p2)){
            ((Database) this.getApplication()).addPerson(p2);
        }

        if(!((Database) this.getApplication()).existsAlready(p3)){
            ((Database) this.getApplication()).addPerson(p3);
        }



        prepareMenu();
        String[] keys = actions.keySet().toArray(new String[actions.keySet().size()]);

        ListView av = (ListView) findViewById(R.id.menu_list);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, keys);

        av.setAdapter(adapter);


        av.setOnItemClickListener((parent, view, position, id) -> {
            String key = (String) parent.getItemAtPosition(position);
            startActivity((Intent) actions.get(key));
        });




    }


    public void addMenuItem(String label, Class<?> cls) {
        actions.put(label, new Intent(this, cls));
    }


}