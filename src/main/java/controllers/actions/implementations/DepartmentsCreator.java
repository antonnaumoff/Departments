package controllers.actions.implementations;

import controllers.actions.Action;
import models.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import repository.DepartmentRepository;
import utils.validators.OvalValidator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component(value = "/Departments/creator")
public class DepartmentsCreator implements Action {

    @Autowired
    @Qualifier("hibernateDepartmentRepository")
    private DepartmentRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();

        Department test = new Department();
        test.setTitle(request.getParameter("title").trim());
        Map<String, String> messages = OvalValidator.validate(test);
        if (messages.isEmpty()) {
            try {
               dataService.createDepartment(test.getTitle());

            } catch (Exception e) {
                (Logger.getLogger("exceptions")).warn("Some message", e);
                request.setAttribute("message", e);
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
            response.sendRedirect("/");
        } else {
            request.setAttribute("message", messages.get("title"));
            request.setAttribute("title", test.getTitle());
            request.getRequestDispatcher("/WEB-INF/jsp/departmentForm.jsp").forward(request, response);
        }
    }
}
