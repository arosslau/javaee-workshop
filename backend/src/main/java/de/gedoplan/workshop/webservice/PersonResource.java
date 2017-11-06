package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Person;
import de.gedoplan.workshop.persistence.PersonRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("person")
@Transactional
public class PersonResource
{
    private static final String ID_TEMPLATE = "{id}";
    private static final String ID_PARAM = "id";

    @Inject
    private PersonRepository repository;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Person> getAllPersons()
    {
        return repository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Person getTalk(@PathParam(ID_PARAM) Integer id)
    {
        Person person = repository.findById(id);

        if (person == null) {
            throw new NotFoundException();
        }

        return person;
    }

    @PUT
    @Path(ID_TEMPLATE)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void putTalk(@PathParam(ID_PARAM) Integer id, Person person)
    {
        if (!person.getId().equals(id)) {
            throw new BadRequestException();
        }

        person.setId(id);

        repository.merge(person);
    }

    @DELETE
    @Path(ID_TEMPLATE)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteTalk(@PathParam(ID_PARAM) int id)
    {
        Person person = repository.findById(id);
        if (person != null) {
            repository.remove(person);
        }

        // nicht machen :-) idem potenz (http): throw new NotFoundException();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postTalk(Person person, @Context UriInfo uriInfo)
    {
        if (person.getId() != null) {
            throw new BadRequestException();
        }

        repository.persist(person);

        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(ID_TEMPLATE)
                .resolveTemplate(ID_PARAM, person.getId())
                .build();

        return Response.created(location).build();
    }
}
