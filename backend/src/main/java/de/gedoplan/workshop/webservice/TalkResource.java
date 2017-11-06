package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkRepository;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Transactional
@Path("talk")
public class TalkResource {

  private static final String ID_PARM = "id";
  private static final String ID_TEMPLATE = "{" + ID_PARM + "}";

  @Inject
  private TalkRepository talkRepository;

  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public List<Talk> getAllTalks() {
    return this.talkRepository.findAll();
  }

  @Path(ID_TEMPLATE)
  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Talk getTalk(@PathParam(ID_PARM) Integer id) {

    Talk talk = this.talkRepository.findById(id);
    if (talk == null) {
      throw new NotFoundException();
    }

    return talk;
  }

  @PUT
  @Path(ID_TEMPLATE)
  @Consumes(MediaType.APPLICATION_JSON)
  public void putTalk(@PathParam(ID_PARM) Integer id, Talk talk) {
    if (talk.getId() == null) {
      talk.setId(id);
    } else if (!talk.getId().equals(id)) {
      throw new BadRequestException();
    }

    this.talkRepository.merge(talk);
  }

  @DELETE
  @Path(ID_TEMPLATE)
  public void deleteTalk(@PathParam(ID_PARM) Integer id) {
    Talk talk = this.talkRepository.findById(id);
    if (talk != null) {
      this.talkRepository.remove(talk);
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postTalk(Talk talk, @Context UriInfo uriInfo) {
    if (talk.getId() != null) {
      throw new BadRequestException();
    }

    this.talkRepository.persist(talk);

    URI location = uriInfo
        .getAbsolutePathBuilder()
        .path(ID_TEMPLATE)
        .resolveTemplate(ID_PARM, talk.getId())
        .build();

    return Response
        .created(location)
        .build();
  }
}
