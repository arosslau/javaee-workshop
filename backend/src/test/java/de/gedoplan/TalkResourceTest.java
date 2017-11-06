package de.gedoplan;

import de.gedoplan.workshop.domain.Talk;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

public class TalkResourceTest {

  @Test
  public void testGet() throws Exception {
    Client client = ClientBuilder.newClient();

    WebTarget baseTarget = client.target("http://localhost:8080/javaee-workshop-backend/rest");

    WebTarget talkTarget = baseTarget.path("talk");

    WebTarget talk1Target = talkTarget.path("1");

    Talk talk = talk1Target
        .request()
        .accept(MediaType.APPLICATION_JSON)
        .get(Talk.class);

    System.out.println(talk);
  }

}
