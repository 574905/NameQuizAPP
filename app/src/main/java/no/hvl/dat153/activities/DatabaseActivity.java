package no.hvl.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.namequizapp.R;

import java.util.ArrayList;
import java.util.List;

import no.hvl.dat153.adapters.DatabaseAdapter;
import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;

public class DatabaseActivity extends MenuActivity {

    ImageButton deleteButton;
    ListView simpleListView;
    List<Person> database;
    Button addPerson;
    PersonDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // declaring the variables
        deleteButton = findViewById(R.id.button_delete);
        db = PersonDatabase.getInstance(this);
        database = db.personDao().getAllPersons();
        addPerson = findViewById(R.id.button_add);

        // when pressing the addPerson button
        addPerson.setOnClickListener(x->{
            Intent i = new Intent(this, AddActivity.class);
            startActivity(i);
        });


        // finding the ListView in question
        simpleListView = findViewById(R.id.simpleListView);

        // create adapter on database view layout
        DatabaseAdapter databaseAdapter =
                new DatabaseAdapter(this, R.layout.list_view_items, database == null ? new ArrayList<>() : database);

        // set adapter to the listview
        simpleListView.setAdapter(databaseAdapter);



    }




    }

