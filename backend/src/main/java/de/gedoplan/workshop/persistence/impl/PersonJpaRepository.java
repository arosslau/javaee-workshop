package de.gedoplan.workshop.persistence.impl;

import de.gedoplan.workshop.domain.Person;
import de.gedoplan.workshop.persistence.PersonRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonJpaRepository implements PersonRepository {

  @Inject
  EntityManager entityManager;

  @Override
  public void persist(Person person) {
    this.entityManager.persist(person);
  }

  @Override
  public Person merge(Person person) {
    return this.entityManager.merge(person);
  }

  @Override
  public void remove(Person person) {
    this.entityManager.remove(person);
  }

  @Override
  public Person findById(Integer id) {
    return this.entityManager.find(Person.class, id);
  }

  @Override
  public List<Person> findAll() {
    return this.entityManager.createQuery("select x from Person x", Person.class).getResultList();
  }
}
