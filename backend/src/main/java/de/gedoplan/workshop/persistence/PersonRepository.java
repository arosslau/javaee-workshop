package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Person;

import java.util.List;

public interface PersonRepository {
  void persist(Person person);

  Person merge(Person person);

  void remove(Person person);

  List<Person> findAll();

  Person findById(Integer id);
}
