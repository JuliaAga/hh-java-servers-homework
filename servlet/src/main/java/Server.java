import Context.Counter;
import Servlets.ClearServlet;
import Servlets.CounterServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Server extends org.eclipse.jetty.server.Server {

    private Counter counter;

    public Server(int port) {
        super(port);
        counter = new Counter();
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setContextPath("/");
        ctx.addServlet(CounterServlet.class, "/counter");
        ctx.addServlet(ClearServlet.class, "/counter/clear");
        ctx.setAttribute("counter", counter);

        return ctx;
    }
}
