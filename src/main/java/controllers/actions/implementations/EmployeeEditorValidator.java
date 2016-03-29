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

@Component(value = "/Employees/edit")
public class EmployeeEditorValidator implements Action {

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = RequestParser.checkString(request.getParameter("id"), request, response);
        String title = request.getParameter("title");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        Integer salary = RequestParser.parseIntWithDefaultValue(request, "salary");
        Date date = RequestParser.parseDateDeafultValue(request, "date");
        Employee test = new Employee();

        test.setId(id);
        test.setJob_title(title);
        test.setFirst_name(firstName);
        test.setSecond_name(secondName);
        test.setSalary(salary);
        test.setDate(date);

        Map messages = OvalValidator.validate(test);
        if (messages.isEmpty()) {

            try {
                dataService.editEmployee(test);
                test.setDep_id(dataService.getId_dById(id));

            } catch (Exception e) {
                (Logger.getLogger("exceptions")).warn("Some message", e);
                request.setAttribute("message", "Some problem with database, please, try later");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }

            response.sendRedirect(request.getServletContext().getContextPath() + "/Employees/list?id_dep=" + test.getDep_id());

        } else {
            request.setAttribute("messages", messages);
            request.setAttribute("id", id);
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
