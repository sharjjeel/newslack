package com.chat.resource;

import com.chat.model.Message;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("threads")
public class ChatResource {

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThreads(@DefaultValue("20") @QueryParam("count") int count) {
        List<String> threads = new ArrayList<String>();
        // TODO: get last {count} updated threads
        return Response.ok(threads.toArray(new String[threads.size()])).build();
    }

    @GET
    @Path("/{thread_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(@PathParam("thread_name") String thread_name,
                                @DefaultValue("20") @QueryParam("count") int count) {
        List<Message> messages = new ArrayList<Message>();
        // TODO: get last {count} messages
        return Response.ok(messages.toArray(new Message[messages.size()])).build();
    }

    @GET
    @Path("/{thread_name}/callouts/unanswered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCallouts(@PathParam("thread_name") String thread_name) {
        List<Message> messages = new ArrayList<Message>();
        // TODO: get the callouts in a specific thread
        return Response.ok(messages.toArray(new Message[messages.size()])).build();
    }


    @POST
    @Path("/{thread_name}/messages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(@PathParam("thread_name") String thread_name, Message m) {
        if (m.ts == null) {
            m.ts = Instant.now().toString();
        }

        if (m.text == null) {
            throw new WebServiceException("text must not be null");
        }

        if (m.user == null) {
            throw new WebServiceException("user must not be null");
        }
        // TODO: Add userId and Message to postgres
        return Response.status(Response.Status.CREATED).build();
    }
}
