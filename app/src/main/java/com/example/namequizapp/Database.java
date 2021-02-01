package com.example.namequizapp;

import android.app.Application;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database = new ArrayList<>();
    private int score = 0;

    public ArrayList<Person> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Person> database) {
        this.database = database;
    }

    public int getScore() {
        return score;
    }

    public void addPerson(Person p) {
        this.database.add(p);
    }



}
