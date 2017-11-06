package de.gedoplan.workshop.persistence;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.domain.TalkType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@ApplicationScoped
@Alternative
public class TalkMockRepository implements TalkRepository
{
    // nicht Threadsafe
    private Map<Integer, Talk> store = new HashMap<>();
    private int idSequence = 1;

    public TalkMockRepository()
    {
        persist(new Talk("Java EE ohne Ballast", TalkType.WORKSHOP, createDateOf(2017, 11, 6, 9, 0), 480));
        persist(new Talk("Eroeffnung", TalkType.KEYNOTE, createDateOf(2017, 11, 7, 9, 0), 45));
        persist(new Talk("DDD mit Java EE", TalkType.SESSION, createDateOf(2017, 11, 7, 15, 0), 75));
    }

    @Override
    public void persist(Talk talk) {
        talk.setId(idSequence++);
        store.put(talk.getId(), talk);
    }

    @Override
    public Talk merge(Talk talk) {
        if (talk.getId() == null) {
            persist(talk);
        } else {
            store.put(talk.getId(), talk);
        }

        return talk;
    }

    @Override
    public boolean remove(Talk talk) {
        return store.remove(talk.getId()) != null;
    }

    @Override
    public Talk findById(Integer id) {
        return store.get(id);
    }

    @Override
    public List<Talk> findAll() {
        return new ArrayList<>(this.store.values());
    }

    private static Date createDateOf(int year, int month, int dayOfMonth, int hour, int minute) {
        return Date.from(ZonedDateTime.of(year, month, dayOfMonth, hour, minute, 0, 0, ZoneId.of("Europe/Berlin")).toInstant());
    }
}
