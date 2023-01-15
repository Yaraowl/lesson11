package ui;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.DAOException;
import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = userService.readByEmail(login);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        if (user != null && user.getPassword().equals(password)) {
            request.setAttribute("userEmail", login);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}


//    private Logger log = Logger.getLogger(LoginServlet.class);
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        log.trace("Opening Login Form...");
//        request.getRequestDispatcher("login.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        log.trace("Getting fields values from Login Form...");
//        request.setCharacterEncoding("UTF-8");
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//
//        User user = null;
//
//        log.trace("Checking fields values for emptiness...");
//        if (!login.isEmpty() && !password.isEmpty()) {
//            try {
//                log.trace("Getting user from database...");
//                user = userService.readByEmail(login);
//            } catch (DAOException e) {
//                log.error("Getting user by email failed!", e);
//            }
//
//            if (user == null) {
//                log.warn("There is no user with login \"" + login + "\" in database!");
//                log.trace("Reopening Login Form...");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            } else {
//                log.trace("Checking user's password for matching database...");
//                if (user.getPassword().equals(password)) {
//                    log.trace("Preparing fields to return...");
//                    request.setAttribute("userFirstName", user.getFirstName());
//                    request.setAttribute("userLastName", user.getLastName());
//                    request.setAttribute("userAction", "авторизировались");
//
//                    log.trace("Redirecting to User's account page...");
//                    request.getRequestDispatcher("cabinet.jsp").forward(request, response);
//                    return;
//                } else {
//                    log.warn("User's password doesn't match database!");
//                    log.trace("Reopening Login Form...");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                }
//            }
//        } else {
//            log.warn("There are still some blank fields yet!");
//            log.trace("Reopening Login Form...");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//    }
//
//}
