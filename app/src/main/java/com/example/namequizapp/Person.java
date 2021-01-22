package com.example.namequizapp;

public class Person {

    String name;
    String image;

    public Person(String name, String image){
        this.name = name;
        this.image = image;
    }

    public void addPerson(Person p){

    }

    public void deletePerson(Person p){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
