package filter;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import domain.AccessLevel;

@WebFilter("/cabinet.jsp")
public class CabinetFilter implements Filter {
    private FilterService filterService = FilterService.getFilterService();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        filterService.doFilterValidation(request, response, chain, Arrays.asList(AccessLevel.USER, AccessLevel.ADMIN));
    }
}
