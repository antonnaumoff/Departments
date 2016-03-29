package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "second", urlPatterns = {"/"})
public class SecondCustomFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("second filter");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
