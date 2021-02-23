import java.util.HashSet;
import java.util.Set;

import Context.Counter;
import Resources.CounterResource;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;

public class JettyServer extends Server {

    private Counter counter;

    public JettyServer(int port) {
        super(port);
        counter = new Counter();
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setAttribute("counter", counter);

        ServletHolder holder = ctx.addServlet(ServletContainer.class, "/*");
        holder.setInitOrder(1);
        holder.setInitParameter("javax.ws.rs.Application", "appConfig");

        return ctx;
    }
}

class appConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CounterResource.class);
        return classes;
    }
}
