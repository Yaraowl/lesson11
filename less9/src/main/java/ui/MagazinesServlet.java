package ui;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dao.DAOException;
import domain.Magazine;
import service.MagazineService;
import service.impl.MagazineServiceImpl;

@WebServlet("/magazines")
public class MagazinesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MagazineService magazineService = MagazineServiceImpl.getMagazineService();

    private Logger log = Logger.getLogger(MagazinesServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Magazine> magazines = null;

        try {
            log.trace("Getting list of magazines from database...");
            magazines = magazineService.readAll();
        } catch (DAOException e) {
            log.error("Getting list of magazines failed!", e);
        }

        if (magazines == null) {
            log.warn("There is no any magazine in database registered!");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("There is no magazines in database!");
        } else {
            log.trace("Returning list of magazines...");
            String json = new Gson().toJson(magazines);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

}
