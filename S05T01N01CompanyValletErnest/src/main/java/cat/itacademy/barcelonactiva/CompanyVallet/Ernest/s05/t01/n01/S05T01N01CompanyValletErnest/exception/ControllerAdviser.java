package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n01.S05T01N01CompanyValletErnest.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler({ElementNotFoundException.class})
    public String handleElementNotFound(ElementNotFoundException ex, Model model) {
        return "index";
    }
}
