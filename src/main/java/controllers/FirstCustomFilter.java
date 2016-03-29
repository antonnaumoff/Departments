package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "first", urlPatterns = {"/"})
public class FirstCustomFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("first filter");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
