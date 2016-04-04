package mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import service.DataService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/")
    public String index(Model model, Principal principal, UriComponentsBuilder uriComponentsBuilder) throws Exception {

        model.addAttribute("department", dataService.getDepartmentList());
        return "departmentList";
    }

    @RequestMapping(value = "/xlsExport", method = RequestMethod.GET)
    public String getExcel(Model model) {
        model.addAttribute("list", dataService.getDepartmentList());
        return "testListExcel";
    }

    @RequestMapping(value = "/pdfExport", method = RequestMethod.GET)
    public String getPdf(Model model) {
        model.addAttribute("list", dataService.getDepartmentList());
        return "testListPdf";
    }

    @RequestMapping(value = "/pdfAsStream", method = RequestMethod.GET)
    public void getPdfAsStream(HttpServletResponse response) throws Exception {

        byte[] data = FileCopyUtils.copyToByteArray(new FileInputStream("/home/antonnaumoff/Documents/departments.pdf"));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=departments.pdf");
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

    @RequestMapping(value = "/xlsAsStream", method = RequestMethod.GET)
    public void getXlsAsStream(HttpServletResponse response) throws Exception {

        byte[] data = FileCopyUtils.copyToByteArray(new FileInputStream("/home/antonnaumoff/Documents/export"));

        response.setContentType("application/x-msexcel");
        response.setHeader("Content-Disposition", "attachment; filename=departments.xls");
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

}
