package de.gedoplan.workshop.persistence.impl;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class TalkJpaRepository extends SingleIdEntityRepository<Integer, Talk> implements TalkRepository {

  // @PersistenceContext(unitName="hugo")
  // @Override
  // public void setEntityManager(EntityManager entityManager) {
  // // TODO Auto-generated method stub
  // super.setEntityManager(entityManager);
  // }

  // List<Talk> findByType(TalkType talkType) { }
}
