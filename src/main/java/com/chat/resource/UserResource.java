package com.chat.resource;

import com.chat.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("users")
public class UserResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/healthcheck")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "I'm alive";
    }

    @GET
    @Path("/{user_name}/callouts/unanswered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCallouts(@PathParam("user_name") String user_name) {
        List<Message> messages = new ArrayList<Message>();
        // TODO: get callouts for a specific user
        return Response.ok(messages.toArray(new Message[messages.size()])).build();
    }
}