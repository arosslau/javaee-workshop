package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Alternative
public class PersonMockRepository implements PersonRepository
{
    private Map<Integer, Person> store = new HashMap<>();
    private int idSequence = 1;

    @Override
    public void persist(Person person) {
        person.setId(idSequence++);
        store.put(person.getId(), person);
    }

    @Override
    public Person merge(Person person)
    {
        if (person.getId() == null) {
            persist(person);
        } else {
            store.put(person.getId(), person);
        }

        return person;
    }

    @Override
    public boolean remove(Person person) {
        return store.remove(person.getId()) != null;
    }

    @Override
    public Person findById(int id) {
        return store.get(id);
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(this.store.values());
    }
}
