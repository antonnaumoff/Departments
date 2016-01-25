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

@Component(value = "/Departments/EditValidator")
public class DepartmentEditorValidator implements Action {

    @Autowired
    @Qualifier("hibernateDepartmentRepository")
    private DepartmentRepository dataService;


    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_dep = (Integer.parseInt(request.getParameter("id_dep")));
        String title = (request.getParameter("title")).trim();
        Department test = new Department();
        test.setId(id_dep);
        test.setTitle(title);
        Map<String, String> messages = OvalValidator.validate(test);
        if (messages.isEmpty()) {
            try {
                dataService.editDepartment(test.getTitle(), test.getId());
            } catch (Exception e) {
                (Logger.getLogger("exceptions")).warn("Some message", e);
                request.setAttribute("message", "Some problem with database, please, try later");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
            response.sendRedirect("/");
        } else {
            request.setAttribute("id_dep", test.getId());
            request.setAttribute("title", test.getTitle());
            request.setAttribute("message", messages.get("title"));
            request.getRequestDispatcher("/WEB-INF/jsp/departmentForm.jsp").forward(request, response);
        }
    }
}

