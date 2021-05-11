package com.example.exercisedb.controllers;

import com.example.exercisedb.dto.responses.BaseErrorResponse;
import com.example.exercisedb.dto.responses.BadRequestResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static com.example.exercisedb.constants.ErrorMessages.CONSTRAINT_VALIDATION_ERROR_MESSAGE;
import static com.example.exercisedb.constants.ErrorMessages.GENERIC_ERROR_MESSAGE;

@ControllerAdvice
public class ControllerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<BadRequestResponse> constraintViolationException(ConstraintViolationException e) {
        logger.error(CONSTRAINT_VALIDATION_ERROR_MESSAGE, e);
        Map<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        for(ConstraintViolation<?> entry: e.getConstraintViolations())
            errors.put(
                    ((PathImpl)entry.getPropertyPath()).getLeafNode().getName(),
                    entry.getMessage()
            );
        BadRequestResponse errorResponse = new BadRequestResponse(errors, status.value(), CONSTRAINT_VALIDATION_ERROR_MESSAGE);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseErrorResponse> genericException(Exception e) {
        logger.error(GENERIC_ERROR_MESSAGE, e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        BaseErrorResponse errorResponse = new BaseErrorResponse(status.value(), GENERIC_ERROR_MESSAGE);
        return new ResponseEntity<>(errorResponse, status);
    }
}
