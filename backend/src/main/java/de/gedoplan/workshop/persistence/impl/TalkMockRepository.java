package de.gedoplan.workshop.persistence.impl;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.domain.TalkType;
import de.gedoplan.workshop.persistence.TalkRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class TalkMockRepository implements TalkRepository {

  private Map<Integer, Talk> store = new HashMap<>();
  private int idGenerator = 1;

  protected TalkMockRepository() {
    persist(new Talk("Java EE ohne Ballast", TalkType.WORKSHOP, createDateOf(2017, 11, 6, 9, 0), 480));
    persist(new Talk("Eroeffnung", TalkType.KEYNOTE, createDateOf(2017, 11, 7, 9, 0), 45));
    persist(new Talk("DDD mit Java EE", TalkType.SESSION, createDateOf(2017, 11, 7, 15, 0), 75));
  }

  private static Date createDateOf(int year, int month, int dayOfMonth, int hour, int minute) {
    return Date.from(ZonedDateTime.of(year, month, dayOfMonth, hour, minute, 0, 0, ZoneId.of("Europe/Berlin")).toInstant());
  }

  @Override
  public void persist(Talk talk) {
    talk.setId(this.idGenerator++);
    this.store.put(talk.getId(), talk);
  }

  @Override
  public Talk merge(Talk talk) {
    if (talk.getId() == null) {
      persist(talk);
    } else {
      this.store.put(talk.getId(), talk);
    }

    return talk;
  }

  @Override
  public boolean remove(Talk talk) {
    return this.store.remove(talk.getId()) != null;
  }

  @Override
  public Talk findById(Integer id) {
    return this.store.get(id);
  }

  @Override
  public List<Talk> findAll() {
    return new ArrayList<>(this.store.values());
  }

}
