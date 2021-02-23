package Servlets;

import Context.Counter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(urlPatterns = "/counter/clear")
public class ClearServlet extends HttpServlet {

    private Counter counter;

    @Override
    public void init() {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Optional<Cookie> cookie = Optional.empty();
        if (req.getCookies() != null) {
            cookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("hh-auth"))
                    .filter(c -> c.getValue().length() > 10)
                    .findAny();
        }
        if (cookie.isPresent()) {
            counter.setCounter(0);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
