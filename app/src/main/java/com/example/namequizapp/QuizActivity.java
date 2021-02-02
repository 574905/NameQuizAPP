package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class QuizActivity extends MenuActivity {

    ImageView qPicture;
    TextView qName;
    ArrayList<Person> database;
    Button submit;
    TextView number;
    int score;
    int total;
    Person person;
    Iterator<Person> it;
    TextView title;
    Drawable done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        database = ((Database) this.getApplication()).getDatabase();
        qPicture = (ImageView) findViewById(R.id.quiz_image);
        qName = (TextView) findViewById(R.id.quiz_name);
        submit = (Button) findViewById(R.id.button_submit);
        number = (TextView) findViewById(R.id.score);
        title = (TextView) findViewById(R.id.quiz_title);
        score = 0;
        total = 0;
        done = getResources().getDrawable(getResources()
                .getIdentifier("done", "drawable", getPackageName()));


        Collections.shuffle(database);
        it = database.iterator();


        newStudent();
    }

    public void submitButtonClicked(View view){
        total++;
        String guess = qName.getText().toString();
        String answer = person.getName();

        if (guess.toLowerCase().equals(answer.toLowerCase())) { // Correct guess
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show();
        } else { // Incorrect guess
            Toast.makeText(this, "Incorrect, correct answer was " + answer + ".", Toast.LENGTH_LONG).show();
        }

        number.setText("Score: " + score + "/"+ total); // Update score
        qName.onEditorAction(EditorInfo.IME_ACTION_DONE); // Hide keyboard
        newStudent();
    }


    public void newStudent() {
        if (it.hasNext()) { // If there are more students in the list
            person = it.next();
            qPicture.setImageDrawable(person.getDrawable());
            qName.setText("");
        } else { // No more students

            title.setText("Final result");
            qPicture.setImageDrawable(done);
            qName.setText("");
            qName.setHint("");
            qName.clearFocus();
            qName.setBackgroundResource(android.R.color.transparent);
            submit.setText("End quiz");
            submit.setOnClickListener(v -> endQuiz());
            qName.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void endQuiz() { // Start main activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }




}
