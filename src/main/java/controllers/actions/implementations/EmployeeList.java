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
import java.util.List;

@Component(value = "/Employees/list")
public class EmployeeList implements Action {

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Employee> empl = null;
        Integer id_dep = RequestParser.checkString((request.getParameter("id_dep")), request, response);
        try {
            empl = dataService.getEmloyeeListById(id_dep);
        } catch (Exception e) {
            (Logger.getLogger("exceptions")).warn("Some message", e);
            request.setAttribute("message", "Some problem with database, please, try later");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
        request.setAttribute("id_dep", id_dep);
        request.setAttribute("list", empl);
        request.getRequestDispatcher("/WEB-INF/jsp/employeeList.jsp").forward(request, response);
    }
}

