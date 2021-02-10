package no.hvl.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.namequizapp.R;

public class MainActivity extends AppCompatActivity {

    Button quiz;
    Button database;
    Button add;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = findViewById(R.id.quiz_button1);
        database = findViewById(R.id.db_button1);
        add = findViewById(R.id.add_button1);

        quiz.setOnClickListener(x->{
            i = new Intent(this, QuizActivity.class);
            startActivity(i);
        });

        database.setOnClickListener(x->{
            i = new Intent(this, DatabaseActivity.class);
            startActivity(i);
        });

        add.setOnClickListener(x->{
            i = new Intent(this, AddActivity.class);
            startActivity(i);
        });


    }


}