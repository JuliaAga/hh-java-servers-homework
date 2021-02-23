package Resources;

import java.util.Date;

import Context.Counter;

import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("counter")
public class CounterResource {

    private ObjectMapper mapper = new ObjectMapper();

    private Counter counter;

    public CounterResource(@Context ServletContext context) {
        counter = (Counter) context.getAttribute("counter");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        ObjectNode response = mapper.createObjectNode()
                .put("date", (new Date()).toString())
                .put("value", counter.getCounter());
        return Response.ok(response).build();
    }

    @POST
    public Response post() {
        counter.incrementAndGet();
        return Response.ok().build();
    }

    @DELETE
    public Response subtract(@HeaderParam("Subtraction-Value") Integer value) {
        if (value == null)
            return Response.status(Status.BAD_REQUEST).build();

        counter.addAndGet(-value);
        return Response.ok().build();
    }

    @POST
    @Path(value = "/clear")
    public Response clear(@CookieParam("hh-auth") String auth) {
        if (auth != null && auth.length() > 10) {
            counter.setCounter(0);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
