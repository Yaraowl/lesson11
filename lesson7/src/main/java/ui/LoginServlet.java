package ui;

import java.io.IOException;

import dto.UserLogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import dao.DAOException;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();

    private Logger log = Logger.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("Opening Login Form...");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("Getting fields values from Login Form...");
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;

        try {
            log.trace("Getting user from database...");
            user = userService.readByEmail(login);
        } catch (DAOException e) {
            log.error("Getting user by email failed!", e);
        }

        if (user == null) {
            log.warn("There is no user with login \"" + login + "\" in database!");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("User with login \"" + login + "\" doesn`t exists!");
        } else {
            log.trace("Checking user's password for matching database...");
            if (user.getPassword().equals(password)) {
                log.trace("Redirecting to User's account page...");
                UserLogin userLogin = new UserLogin(user.getEmail(), "jsp/cabinet.jsp");
                String json = new Gson().toJson(userLogin);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } else {
                log.warn("User's password doesn't match database!");
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("You entered wrong password!");
            }
        }

    }}