package no.hvl.dat153;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.namequizapp.R;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import no.hvl.dat153.activities.QuizActivity;
import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;
import no.hvl.dat153.dao.PersonDAO;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestScore {

    private QuizActivity quizActivity;

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @BeforeClass
    public static void beforeTest() {
        List<Person> database;
        PersonDAO personDao;

        Context context = ApplicationProvider.getApplicationContext();
        PersonDatabase personDatabase = PersonDatabase.getInstance(context);
        personDao = personDatabase.personDao();
        database = personDao.getAllPersons();

        if(database.isEmpty()){
            personDao.insertPerson(new Person("Bendik", ContextCompat.getDrawable(context,
                    R.drawable.bendik)));
        }
    }

    @Test
    public void scoreIsCorrect(){
        activityRule.getScenario().onActivity(x -> quizActivity = x);

        // if empty, it throws an AssertionError
        assertFalse(quizActivity.shuffledDatabase.isEmpty());

        // getting the first name what shows in the quiz
        String name = quizActivity.shuffledDatabase.get(0).getName();

        // typing the name we've found above
        onView(withId(R.id.quiz_name)).perform(typeText(name), closeSoftKeyboard());

        // submitting the name to see whether it's right or wrong
        onView(withId(R.id.button_submit)).perform(click());

        // the test goes through if the score is updated accordingly
        onView(withId(R.id.score)).check(matches(withSubstring("Score: 1/1")));


    }

    @Test
    public void scoreIsNotCorrect() {
        activityRule.getScenario().onActivity(x -> quizActivity = x);

        // if empty, it throws an AssertionError
        assertFalse(quizActivity.shuffledDatabase.isEmpty());

        // typing in a random string (in this case "Wrong") to see the outcome
        onView(withId(R.id.quiz_name)).perform(typeText("Wrong"), closeSoftKeyboard());

        // submitting to see whether the score is updated
        onView(withId(R.id.button_submit)).perform(click());

        // the test goes through if the score is updated accordingly
        onView(withId(R.id.score)).check(matches(withSubstring("Score: 0/1")));
    }
}
