package com.example.namequizapp;

import android.app.Application;

import java.util.ArrayList;

public class Database extends Application {
    private ArrayList<Person> database = new ArrayList<>();

    public ArrayList<Person> getDatabase() {
        return database;
    }

    public void addPerson(Person p) {
        this.database.add(p);
    }

    public void deletePerson(Person p){

    }
}
