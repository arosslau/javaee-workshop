package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkRepository;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
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

@Path(TalkResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TalkResource {

  public static final String PATH = "talk";
  public static final String ID_TEMPLATE = "id";
  public static final String ID_PATH = "{" + ID_TEMPLATE + "}";

  @Inject
  TalkRepository talkRepository;

  @GET
  public List<Talk> getTalks() {
    return this.talkRepository.findAll();
  }

  @GET
  @Path(ID_PATH)
  public Talk getTalk(@PathParam(ID_TEMPLATE) Integer id) {
    Talk talk = this.talkRepository.findById(id);
    if (talk == null) {
      throw new NotFoundException();
    }

    return talk;
  }

  @PUT
  @Path(ID_PATH)
  public void putTalk(@PathParam(ID_TEMPLATE) Integer id, Talk talk) {

    if (!id.equals(talk.getId())) {
      throw new BadRequestException("Ids in url and talk must match");
    }

    if (this.talkRepository.findById(id) == null) {
      throw new NotFoundException();
    }

    this.talkRepository.merge(talk);
  }

  @DELETE
  @Path(ID_PATH)
  public void deleteTalk(@PathParam(ID_TEMPLATE) Integer id) {
    Talk talk = this.talkRepository.findById(id);
    if (talk != null) {
      this.talkRepository.remove(talk);
    }
  }

  @POST
  public Response postTalk(Talk talk, @Context UriInfo uriInfo) {
    if (talk.getId() != null) {
      throw new BadRequestException("Id must not be set");
    }

    this.talkRepository.persist(talk);

    URI uri = uriInfo.getAbsolutePathBuilder()
        .path(ID_PATH)
        .resolveTemplate(ID_TEMPLATE, talk.getId())
        .build();

    return Response.created(uri).build();

  }

}
