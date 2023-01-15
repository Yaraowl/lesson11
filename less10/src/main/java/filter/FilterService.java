package filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import domain.AccessLevel;

public class FilterService {

    private static FilterService filterService;

    private FilterService() {
    }

    public static FilterService getFilterService() {
        if (filterService == null) {
            filterService = new FilterService();
        }

        return filterService;
    }

    public void doFilterValidation(ServletRequest request, ServletResponse response, FilterChain chain,
                                   List<AccessLevel> accessLevelList) throws IOException, ServletException {

        try {
            HttpSession session = ((HttpServletRequest) request).getSession();
            AccessLevel accessLevel = AccessLevel.valueOf((String) session.getAttribute("accessLevel"));

            if (accessLevel != null && accessLevelList.contains(accessLevel)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            ((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
