package home.springframework.practice.spring6restmvc.exceptions;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity notFoundHandler() {
//        System.out.println("Exception Occurred");
//        return ResponseEntity.notFound().build();
//    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity transactionSystemException(TransactionSystemException e) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();
        if(e.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException vc = (ConstraintViolationException) e.getCause().getCause();

            List errorList = vc.getConstraintViolations().stream().map(
                    violation -> {
                        Map<String,String> errorMap = new HashMap<>();
                        errorMap.put(String.valueOf(violation.getPropertyPath()), violation.getMessage());
                        return errorMap;
                    }
            ).collect(Collectors.toList());

            return responseEntity.body(errorList);
        }


        return responseEntity.build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity notFoundHandler(MethodArgumentNotValidException e) {
        System.out.println("Exception Occurred");
        List errorList = e.getFieldErrors().stream().map(
                fieldError -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(fieldError.getField(),fieldError.getDefaultMessage());
                    return map;
                }
        ).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }
}
