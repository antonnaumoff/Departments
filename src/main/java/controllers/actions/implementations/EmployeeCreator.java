package controllers.actions.implementations;

import controllers.actions.Action;
import models.Employee;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import repository.EmployeeRepository;
import utils.parsers.RequestParser;
import utils.validators.OvalValidator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@Component(value = "/Employees/employeeCreator")
public class EmployeeCreator implements Action {

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map messages = null;
        Integer id_dep = RequestParser.checkString((request.getParameter("id_dep")), request, response);
        String title = request.getParameter("title");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        Integer salary = RequestParser.parseIntWithDefaultValueO(request, "salary");
        Date date = RequestParser.parseDateDeafultValue(request, "date");
        Employee test = new Employee();
        test.setDep_id(id_dep);
        test.setJob_title(title);
        test.setFirst_name(firstName);
        test.setSecond_name(secondName);
        test.setSalary(salary);
        test.setDate(date);

        messages = OvalValidator.validate(test);
        if (messages.isEmpty()) {
            try {
                dataService.createEmployee(id_dep, title, firstName, secondName, salary, date);
            } catch (Exception e) {
                (Logger.getLogger("exceptions")).warn("Some message", e);
                request.setAttribute("message", "Some problem with database, please, try later");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
            response.sendRedirect(request.getServletContext().getContextPath()+"/Employees/list?id_dep=" + id_dep);
        } else {
            request.setAttribute("messages", messages);
            request.setAttribute("id_dep", id_dep);
            request.setAttribute("message", "Please, fill form correctly");
            request.setAttribute("title", title);
            request.setAttribute("firstName", firstName);
            request.setAttribute("secondName", secondName);
            request.setAttribute("salary", salary);
            request.setAttribute("date", date);
            request.getRequestDispatcher("/WEB-INF/jsp/employeeForm.jsp").forward(request, response);
        }
    }
}

