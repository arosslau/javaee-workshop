package de.gedoplan.workshop.presentation;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkJpaRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("talks")
@RequestScoped
public class TalkPresenter
{
    private List<Talk> talks;

    @Inject
    public TalkPresenter(TalkJpaRepository repository)
    {
        talks = repository.findAll();
    }

    public String getHello()
    {
        return "Hello!";
    }

    public List<Talk> getTalks()
    {
        return talks;
    }
}
