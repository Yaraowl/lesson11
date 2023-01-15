package ui;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;

import org.apache.log4j.Logger;
@WebServlet("/loggingOut")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        log.trace("Logging out...");
        HttpSession session = request.getSession();

        log.trace("Invalidating current session...");
        if (session != null) {
            session.invalidate();
        }

        log.trace("Redirecting to Login page...");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("login.jsp");
    }

}