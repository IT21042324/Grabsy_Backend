package com.grabsy.GrabsyBackend.advice;

import com.grabsy.GrabsyBackend.constant.common.ErrorConstant;
import com.grabsy.GrabsyBackend.exception.ProductNotFoundException;
import com.grabsy.GrabsyBackend.exception.ReviewNotFoundException;
import com.grabsy.GrabsyBackend.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class ExceptionAdvice {
    private final ErrorMessage errorMessage;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public ExceptionAdvice() {
        this.errorMessage = new ErrorMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String productNotFoundException(ProductNotFoundException exception, HttpServletRequest request,
                                           HandlerMethod handler) {
        errorMessage.setErrorMessage(exception, request, handler);
        String formattedErrorMessage = errorMessage.getFormattedErrorMessage();

        log.error(formattedErrorMessage);
        return formattedErrorMessage;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public String reviewNotFoundException(ReviewNotFoundException exception,
                                          HttpServletRequest request,
                                          HandlerMethod handler) {
        errorMessage.setErrorMessage(exception, request, handler);
        String formattedErrorMessage = errorMessage.getFormattedErrorMessage();

        log.error(formattedErrorMessage);
        return formattedErrorMessage;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                  HttpServletRequest request,
                                                  HandlerMethod handler) {

        LinkedHashMap<String, String> additionalInfo = new LinkedHashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            additionalInfo.put(fieldName, errorMessage);
        });

        errorMessage.setErrorMessageForRequestBodyValidation(exception, request, handler);
        String formattedErrorMessage = errorMessage.getFormattedErrorMessage(ErrorConstant.VALIDATION_EXPECTED,
                additionalInfo);
        log.error(formattedErrorMessage);

        return formattedErrorMessage;
    }
}
