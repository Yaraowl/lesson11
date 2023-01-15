package ui;

import java.io.IOException;
import java.time.LocalDate;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.DAOException;
import domain.Subscribe;
import service.SubscribeService;
import service.impl.SubscribeServiceImpl;

@WebServlet("/subscribe")
public class SubscribeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();

    private Logger log = Logger.getLogger(SubscribeServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        log.trace("Getting fields values...");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");
        String magazineID = request.getParameter("magazineID");

        Subscribe subscribe = new Subscribe(userId, Integer.parseInt(magazineID), true, LocalDate.now(), 10);

        try {
            log.trace("Saving subscribe in database...");
            subscribeService.insert(subscribe);
        } catch (DAOException e) {
            log.error("Creating subscribe failed!", e);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

}
