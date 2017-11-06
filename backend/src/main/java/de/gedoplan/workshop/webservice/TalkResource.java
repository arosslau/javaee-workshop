package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.persistence.TalkRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("talk")
@Transactional
public class TalkResource
{
    private static final String ID_TEMPLATE = "{id}";
    private static final String ID_PARAM = "id";

    @Inject
    private TalkRepository repository;

//    @Inject
//    public TalkResource(TalkRepository repository)
//    {
//        this.repository = repository;
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Talk> getAllTalks()
    {
        return repository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Talk getTalk(@PathParam(ID_PARAM) Integer id)
    {
        Talk talk = repository.findById(id);

        if (talk == null) {
            throw new NotFoundException();
        }

        return talk;
    }

    @PUT
    @Path(ID_TEMPLATE)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void putTalk(@PathParam(ID_PARAM) Integer id, Talk talk)
    {
        if (!talk.getId().equals(id)) {
            throw new BadRequestException();
        }

        if (talk.getId() == null) {
            talk.setId(id);
        }

        repository.merge(talk);
    }

    @DELETE
    @Path(ID_TEMPLATE)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteTalk(@PathParam(ID_PARAM) int id)
    {
        Talk talk = repository.findById(id);
        if (talk != null) {
            repository.remove(talk);
        }

        // nicht machen :-) idem potenz (http): throw new NotFoundException();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postTalk(Talk talk, @Context UriInfo uriInfo)
    {
        if (talk.getId() != null) {
            throw new BadRequestException();
        }

        repository.persist(talk);

        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(ID_TEMPLATE)
                .resolveTemplate(ID_PARAM, talk.getId())
                .build();

        return Response.created(location).build();
    }
}
