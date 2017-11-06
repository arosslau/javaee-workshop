package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkRepository;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("talk")
public class TalkResource {

  @Inject
  private TalkRepository talkRepository;

  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public List<Talk> getAllTalks() {
    return this.talkRepository.findAll();
  }

  @Path("{id}")
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Talk getTalk(@PathParam("id") Integer id) {
    Talk talk = this.talkRepository.findById(id);
    return talk;
  }
}
