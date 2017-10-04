package de.gedoplan.workshop.persistence.impl;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Alternative
@RequestScoped
@Stateful
public class ExtendedEntityManagerProducer {
  @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  @Produces
  @RequestScoped
  public EntityManager getEntityManager() {
    return this.entityManager;
  }

}
