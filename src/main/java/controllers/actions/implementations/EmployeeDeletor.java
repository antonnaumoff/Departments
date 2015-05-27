package controllers.actions.implementations;

import controllers.actions.Action;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import repository.EmployeeRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "/employeeDeleting")
public class EmployeeDeletor implements Action {

    @Autowired
    @Qualifier("hibernateEmployeeRepository")
    private EmployeeRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int id_dep = Integer.parseInt(request.getParameter("id_dep"));
        try {
            dataService.deleteById(id);
            response.sendRedirect(request.getServletContext().getContextPath()+"/Employees/list?id_dep=" + id_dep);
        } catch (Exception e) {
            (Logger.getLogger("exceptions")).warn("Some message", e);
            request.setAttribute("message", "Some problem with database, please, try later");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
