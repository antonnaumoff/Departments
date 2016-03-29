package controllers;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstCustomListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(FirstCustomListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        ApplicationContext contextApp = new ClassPathXmlApplicationContext("applicationContext.xml");
        servletContext.setAttribute("springContext", contextApp);
        logger.info("SpringContext has been initialized");
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
