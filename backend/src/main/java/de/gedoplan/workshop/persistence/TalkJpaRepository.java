package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Talk;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class TalkJpaRepository implements TalkRepository
{
    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(Talk talk)
    {
        entityManager.persist(talk);
    }

    @Override
    public Talk merge(Talk talk)
    {
        return entityManager.merge(talk);
    }

    @Override
    public boolean remove(Talk talk)
    {
        entityManager.remove(talk);

        return true;
    }

    @Override
    public Talk findById(Integer id) {
        return entityManager.find(Talk.class, id);
    }

    @Override
    public List<Talk> findAll() {
        return entityManager
            .createQuery("select t from Talk t", Talk.class)
            .getResultList();
    }
}
