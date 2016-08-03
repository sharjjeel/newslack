package com.chat.resource;

import com.chat.DAO.MessageDAO;
import com.chat.DAO.ThreadDAO;
import com.chat.model.Message;
import com.chat.model.Thread;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("threads")
public class ChatResource {

    Logger log = LoggerFactory.logger(ChatResource.class);
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
        List<Thread> threads = new ArrayList<Thread>();
        // TODO: get last {count} updated threads
        ThreadDAO threadDAO = new ThreadDAO();
        return Response.ok(threadDAO.getLastUpdated(count)).build();
    }

    @GET
    @Path("messages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages() {
        log.info("getting messages");
        MessageDAO messageDAO = new MessageDAO();
        log.info(messageDAO.getAllMessages());
        return Response.ok(messageDAO.getAllMessages()).build();
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
    @Path("{thread_name}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(@PathParam("thread_name") String thread_name, Message m) {
        log.info("inside post");
        log.info(m);
        if (m.ts == null) {
            m.ts = Instant.now().toString();
        }

        if (m.text == null) {
            throw new WebServiceException("text must not be null");
        }

        if (m.user_name == null) {
            throw new WebServiceException("user must not be null");
        }
        String uniqueID = UUID.randomUUID().toString();
        m.message_id = uniqueID;
        m.thread_name = thread_name;
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.addMessage(m);

        // TODO: Add userId and Message to postgres
        return Response.ok(m).build();
    }
}
