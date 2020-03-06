package az.task.demo.CustomExceptions;

import az.task.demo.CustomExceptions.BaseExceptionClass.BaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFound.class,TaskNotFound.class,StatusNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(BaseNotFoundException ex, HttpServletRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message",  ex.getMessage() +" "+ex.getId() );
        body.put("HTTP_STATUS","404");
        body.put("path", request.getRequestURI());
        return  new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }
}
