package no.hvl.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namequizapp.R;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;


public class QuizActivity extends MenuActivity {

    ImageView qPicture;
    Button submit;
    TextView number;
    int score;
    int total;
    Person person;
    Iterator<Person> it;
    TextView title;
    PersonDatabase db;

    public TextView qName;
    public List<Person> shuffledDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // declaring the variables
        db = PersonDatabase.getInstance(this);
        shuffledDatabase = db.personDao().getAllPersons();
        qPicture = findViewById(R.id.quiz_image);
        qName = findViewById(R.id.quiz_name);
        submit = findViewById(R.id.button_submit);
        number = findViewById(R.id.score);
        title = findViewById(R.id.quiz_title);

        // keep track on the score and total persons you have guessed in the quiz
        score = 0;
        total = 0;



        // shuffles the list and making it in random order
        Collections.shuffle(shuffledDatabase);

        // making an Iterator to go through the list
        it = shuffledDatabase.iterator();

        // to see if there's any students left in the quiz, if not end the quiz
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
            qPicture.setImageDrawable(person.getImage());
            qName.setText("");
        } else { // No more students

            title.setText(R.string.finalResult);
            qName.setText("");
            qName.setHint("");
            qName.clearFocus();
            qName.setBackgroundResource(android.R.color.transparent);
            submit.setText(R.string.endQuiz);
            qPicture.setVisibility(View.INVISIBLE);
            submit.setOnClickListener(v -> endQuiz());
            qName.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public void endQuiz() { // Start main activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }




}
