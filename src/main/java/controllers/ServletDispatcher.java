package controllers;

import controllers.actions.Action;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ServletDispatcher extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext contextApp;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(contextApp==null)contextApp = (ApplicationContext) request.getServletContext().getAttribute("springContext");

        Action action = (Action) contextApp.getBean(request.getServletPath());

        logger.info("Request arrived with parameter action: "+ request.getServletPath());

        if (action != null) {
            action.execute(request, response);
        }
    }
}

