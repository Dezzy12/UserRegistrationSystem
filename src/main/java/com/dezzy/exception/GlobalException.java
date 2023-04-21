package com.dezzy.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<CustomErrorType> handleResourceNotFoundException(ResourceNotFoundException ex){
        CustomErrorType customErrorType = new CustomErrorType();
        customErrorType.setMessage(ex.errorMessage);
        customErrorType.setStatus(HttpStatus.NOT_FOUND.value());
        customErrorType.setPath(ex.path);
        return new ResponseEntity<CustomErrorType>(customErrorType, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler
    public ResponseEntity<CustomErrorType> handleNoDataFoundException(NoDataFoundException ex){
        CustomErrorType customErrorType = new CustomErrorType();
        customErrorType.setMessage(ex.getMessage());
        customErrorType.setStatus(HttpStatus.NO_CONTENT.value());
        return new ResponseEntity<CustomErrorType>(customErrorType, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorType> handleDataExistException(DataExistException ex){
        CustomErrorType customErrorType = new CustomErrorType();
        customErrorType.setMessage(ex.errorMessage);
        customErrorType.setStatus(HttpStatus.CONFLICT.value());
        customErrorType.setPath(ex.path);
        return new ResponseEntity<CustomErrorType>(customErrorType, HttpStatus.CONFLICT);
    }
}
