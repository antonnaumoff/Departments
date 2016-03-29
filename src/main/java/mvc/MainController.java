package mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DataService;

@Controller
public class MainController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/index.html")
    public String index(Model model) throws Exception {

        model.addAttribute("department", dataService.getDepartmentList());
        return "departmentList";
    }
}
