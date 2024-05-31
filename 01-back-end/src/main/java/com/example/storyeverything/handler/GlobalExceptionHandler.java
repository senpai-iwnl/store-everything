package com.example.storyeverything.handler;

import com.example.storyeverything.exception.DuplicateLoginException;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.exception.InformationAccessDeniedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateLoginException.class)
    public ResponseEntity<Object> handleDuplicateLogin(DuplicateLoginException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<?> handleFieldNotNotFoundException(FieldNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InformationAccessDeniedException.class)
    public ResponseEntity<?> handleInformationAccessDeniedException(InformationAccessDeniedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
