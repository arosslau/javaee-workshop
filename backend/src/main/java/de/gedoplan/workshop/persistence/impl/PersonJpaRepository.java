package de.gedoplan.workshop.persistence.impl;

import de.gedoplan.workshop.domain.Person;
import de.gedoplan.workshop.persistence.PersonRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonJpaRepository implements PersonRepository {

  @PersistenceContext(unitName = "default")
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
  public boolean remove(Person person) {
    this.entityManager.remove(person);
    // TODO Q&D
    return true;
  }

  @Override
  public Person findById(Integer id) {
    return this.entityManager.find(Person.class, id);
  }

  @Override
  public List<Person> findAll() {
    return this.entityManager.createQuery("select p from Person p", Person.class).getResultList();
  }

}
