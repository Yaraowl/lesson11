package ui;
import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.DAOException;
import domain.Magazine;
import service.MagazineService;
import service.impl.MagazineServiceImpl;


    public class MagazineServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        private MagazineService magazineService = MagazineServiceImpl.getMagazineService();

        private Logger log = Logger.getLogger(RegistrationServlet.class);

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.trace("Getting fields values from Magazine creation Form...");
            request.setCharacterEncoding("UTF-8");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String publishDate = request.getParameter("publishDate");
            String subscribePrice = request.getParameter("subscribePrice");

            try {
                log.trace("Saving magazine in database...");
                magazineService.insert(new Magazine(title, description, LocalDate.parse(publishDate), Integer.parseInt(subscribePrice)));
            } catch (DAOException e) {
                log.error("Creating magazine failed!", e);
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Magazine \"" + title + "\" successfully added!");
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        }

    }

