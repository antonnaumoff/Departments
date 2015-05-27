package controllers.actions.implementations;

import controllers.actions.Action;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import repository.DepartmentRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "/Departments/delete")
public class DepartmentsDeletor implements Action {

    @Autowired
    @Qualifier("hibernateDepartmentRepository")
    private DepartmentRepository dataService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            dataService.deleteDepartment(id);
            response.sendRedirect(request.getServletContext().getContextPath()+"/");
        } catch (Exception e) {
            (Logger.getLogger("exceptions")).warn("Some message", e);
            request.setAttribute("message", "Some problem with database, please, try later");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
