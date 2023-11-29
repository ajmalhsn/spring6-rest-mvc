package home.springframework.practice.spring6restmvc.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundHandler() {
        System.out.println("Exception Occurred");
        return ResponseEntity.notFound().build();
    }
}
