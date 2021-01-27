package com.example.namequizapp;

public class Person {

    String name;


    public Person(String name){
        this.name = name;

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '}';
    }
}
