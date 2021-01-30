package com.example.namequizapp;

import android.graphics.drawable.Drawable;

public class Person {

    String name;
    Drawable drawable;


    public Person(String name, Drawable drawable){
        this.name = name;
        this.drawable = drawable;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}
