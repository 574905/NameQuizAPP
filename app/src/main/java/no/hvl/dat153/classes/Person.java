package no.hvl.dat153.classes;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "person_table")
public class Person {



    @PrimaryKey(autoGenerate = true)
    private int id;

    // variables
    private String name;
    private Drawable image;

     // constructor
    public Person(String name, Drawable image){
        this.name = name;
        this.image = image;
    }

    // getters
    public String getName() {
        return name;
    }
    public Drawable getImage() {
        return image;
    }
    public int getId() {
        return id;
    }

    // setters
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", byte=" + image +
                '}';
    }
}
