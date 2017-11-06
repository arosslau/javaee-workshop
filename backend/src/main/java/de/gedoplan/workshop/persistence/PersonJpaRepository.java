package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonJpaRepository implements PersonRepository
{
    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(Person person)
    {
        entityManager.persist(person);
    }

    @Override
    public Person merge(Person person)
    {
        return entityManager.merge(person);
    }

    @Override
    public boolean remove(Person person)
    {
        entityManager.remove(person);

        return true;
    }

    @Override
    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }
}
