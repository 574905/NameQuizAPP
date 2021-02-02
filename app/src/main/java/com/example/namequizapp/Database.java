package com.example.namequizapp;

import android.app.Application;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database = new ArrayList<>();

    public ArrayList<Person> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Person> database) {
        this.database = database;
    }

    public void addPerson(Person p) {
        this.database.add(p);
    }

    public boolean existsAlready(Person p) {
        boolean outcome = false;

            for (Person person : database) {
                if (person.getName().equals(p.getName())) {
                    outcome = true;
                }
            }



    return outcome;
    }
    }
