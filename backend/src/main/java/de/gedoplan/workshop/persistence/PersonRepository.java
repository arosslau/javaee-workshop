package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Person;

import java.util.List;

public interface PersonRepository
{
    void persist(Person person);
    Person merge(Person person);
    boolean remove(Person person);
    Person findById(int id);
    List<Person> findAll();
}
