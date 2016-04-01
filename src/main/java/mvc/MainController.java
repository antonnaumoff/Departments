package mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.DataService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest httpRequest) throws Exception {

        model.addAttribute("department", dataService.getDepartmentList());
        return "departmentList";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String getExcel(Model model) {
        model.addAttribute("list", dataService.getDepartmentList());
        return "testListExcel";
    }
}
