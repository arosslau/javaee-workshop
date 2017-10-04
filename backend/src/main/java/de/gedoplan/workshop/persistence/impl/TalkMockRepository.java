package de.gedoplan.workshop.persistence.impl;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.domain.TalkType;
import de.gedoplan.workshop.persistence.TalkRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

@ApplicationScoped
public class TalkMockRepository implements TalkRepository {

  private Map<Integer, Talk> mockTable = new ConcurrentHashMap<>();
  private AtomicInteger idGenerator = new AtomicInteger(1);

  @PostConstruct
  void postConstruct() {
    persist(new Talk("Java EE ohne Ballast", TalkType.WORKSHOP, createDateOf(2017, 11, 6, 9, 0), 480));
    persist(new Talk("Eroeffnung", TalkType.KEYNOTE, createDateOf(2017, 11, 7, 9, 0), 45));
    persist(new Talk("DDD mit Java EE", TalkType.SESSION, createDateOf(2017, 11, 7, 15, 0), 75));
  }

  private static Date createDateOf(int year, int month, int dayOfMonth, int hour, int minute) {
    return Date.from(ZonedDateTime.of(year, month, dayOfMonth, hour, minute, 0, 0, ZoneId.of("Europe/Berlin")).toInstant());
  }

  @Override
  public void persist(Talk talk) {
    if (talk.getId() != null) {
      throw new PersistenceException("id already set");
    }

    talk.setId(this.idGenerator.getAndIncrement());
    this.mockTable.put(talk.getId(), talk);
  }

  @Override
  public Talk merge(Talk talk) {
    if (talk.getId() == null) {
      persist(talk);
    } else {
      this.mockTable.put(talk.getId(), talk);
    }
    return talk;
  }

  @Override
  public void remove(Talk talk) {
    this.mockTable.remove(talk.getId());
  }

  @Override
  public Talk findById(Integer id) {
    return this.mockTable.get(id);
  }

  @Override
  public List<Talk> findAll() {
    return new ArrayList<>(this.mockTable.values());
  }

}
