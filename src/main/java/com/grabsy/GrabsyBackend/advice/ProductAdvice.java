package com.grabsy.GrabsyBackend.advice;
import com.grabsy.GrabsyBackend.exception.ProductNotFoundException;
import com.grabsy.GrabsyBackend.model.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
public class ProductAdvice {
    private final ErrorMessage errorMessage;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public ProductAdvice(){
        this.errorMessage = new ErrorMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String productNotFoundException(ProductNotFoundException exception, HttpServletRequest request,
                                           HandlerMethod handler){
        errorMessage.setErrorMessage(exception, request, handler);
        String formattedErrorMessage = errorMessage.getFormattedErrorMessage();

        log.error(formattedErrorMessage);
        return formattedErrorMessage;
    }
}
