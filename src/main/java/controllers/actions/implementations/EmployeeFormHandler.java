package controllers.actions.implementations;

import controllers.actions.Action;
import models.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import repository.EmployeeRepository;
import utils.parsers.RequestParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "/employeeFormHandler")
public class EmployeeFormHandler implements Action{

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = null;
        Integer id_dep = null;

        if ((id = RequestParser.parseIntWithDefaultValue(request, "id")) == null) {
            if ((id_dep = RequestParser.parseIntWithDefaultValue(request, "id_dep")) == null) {

                request.setAttribute("message", "This page not available, try later");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            } else {
                request.setAttribute("id_dep", id_dep);
                request.getRequestDispatcher("/WEB-INF/jsp/employeeForm.jsp").forward(request, response);
            }
        } else {
            try {
                Employee empl = dataService.getEmloyeeById(id);
                request.setAttribute("id", id);
                request.setAttribute("title", empl.getJob_title());
                request.setAttribute("firstName", empl.getFirst_name());
                request.setAttribute("secondName", empl.getSecond_name());
                request.setAttribute("salary", empl.getSalary());
                request.setAttribute("date", empl.getDate());
                request.getRequestDispatcher("/WEB-INF/jsp/employeeForm.jsp").forward(request, response);
            } catch (Exception e) {
                (Logger.getLogger("exceptions")).warn("Some message", e);
                request.setAttribute("message", "Some problem with database, please, try later");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        }
    }
}
