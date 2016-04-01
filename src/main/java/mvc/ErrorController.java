package mvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
