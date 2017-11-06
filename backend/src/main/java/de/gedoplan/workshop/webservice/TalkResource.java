package de.gedoplan.workshop.webservice;

import de.gedoplan.workshop.domain.Talk;
import de.gedoplan.workshop.domain.TalkType;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("talk")
public class TalkResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Talk getTalk() {
    Talk talk = new Talk("xxx", TalkType.KEYNOTE, new Date(), 45);
    talk.setId(42);

    return talk;
  }
}
