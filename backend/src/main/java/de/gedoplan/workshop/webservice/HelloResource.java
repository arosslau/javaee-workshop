package de.gedoplan.workshop.webservice;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResource
{
    @GET
    @Path("{name}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String sayHello(@PathParam("name") String name)
    {
        return "Hello " + name + "!";
    }
}
