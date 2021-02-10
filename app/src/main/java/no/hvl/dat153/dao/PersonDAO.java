package no.hvl.dat153.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import no.hvl.dat153.classes.Person;

@Dao
public interface PersonDAO {

    @Insert
    void insertPerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM person_table")
    List<Person> getAllPersons();
}
