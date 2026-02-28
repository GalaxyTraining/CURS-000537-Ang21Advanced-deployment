package pe.edu.galaxy.training.java.apis.api_ventas.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ CustomBadRequest.class })
    public ResponseEntity<Object> handleGlobalException(CustomBadRequest exception) {
        Map<String,String> errors=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(e->{
            errors.put(e.getField(),e.getDefaultMessage());
        });
        errors.forEach(log::info);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

}
