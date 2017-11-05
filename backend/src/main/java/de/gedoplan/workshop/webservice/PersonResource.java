package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Person;
import de.gedoplan.workshop.persistence.PersonRepository;

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

@Path(PersonResource.PATH)
public class PersonResource {

  public static final String PATH = "person";
  public static final String ID_TEMPLATE = "id";
  public static final String ID_PATH = "{" + ID_TEMPLATE + "}";

  @Inject
  PersonRepository personRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> getPersons() {
    return this.personRepository.findAll();
  }

  @GET
  @Path(ID_PATH)
  @Produces(MediaType.APPLICATION_JSON)
  public Person getPerson(@PathParam(ID_TEMPLATE) Integer id) {
    Person person = this.personRepository.findById(id);
    if (person == null) {
      throw new NotFoundException();
    }

    return person;
  }

  @PUT
  @Path(ID_PATH)
  @Consumes(MediaType.APPLICATION_JSON)
  public void putPerson(@PathParam(ID_TEMPLATE) Integer id, Person person) {

    if (!id.equals(person.getId())) {
      throw new BadRequestException("Ids in url and person must match");
    }

    if (this.personRepository.findById(id) == null) {
      throw new NotFoundException();
    }

    this.personRepository.merge(person);
  }

  @DELETE
  @Path(ID_PATH)
  @Consumes(MediaType.APPLICATION_JSON)
  public void deletePerson(@PathParam(ID_TEMPLATE) Integer id) {
    Person person = this.personRepository.findById(id);
    if (person != null) {
      this.personRepository.remove(person);
    }
  }

  @POST
  public Response postPerson(Person person, @Context UriInfo uriInfo) {
    if (person.getId() != null) {
      throw new BadRequestException("Id must not be set");
    }

    this.personRepository.persist(person);

    URI uri = uriInfo.getAbsolutePathBuilder()
        .path(ID_PATH)
        .resolveTemplate(ID_TEMPLATE, person.getId())
        .build();

    return Response.created(uri).build();

  }

}
