package Servlets;

import Context.Counter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    private Counter counter;

    @Override
    public void init() {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(counter.getCounter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        counter.incrementAndGet();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int value = Integer.parseInt(req.getHeader("Subtraction-Value"));
            counter.addAndGet(-value);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
