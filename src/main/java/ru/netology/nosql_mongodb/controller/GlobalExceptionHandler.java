package ru.netology.nosql_mongodb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.nosql_mongodb.exception.ErrorFindUserException;
import ru.netology.nosql_mongodb.exception.ErrorInputDataException;
import ru.netology.nosql_mongodb.model.ResponseError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> catchResourceArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(getErrorResponse("Ошибка валидации: " + ex.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorInputDataException.class)
    public ResponseEntity<Object> catchResourceErrorErrorInputDataException(ErrorInputDataException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorFindUserException.class)
    public ResponseEntity<Object> catchResourceErrorErrorInputDataException(ErrorFindUserException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseError getErrorResponse(String msg, HttpStatus status) {
        return new ResponseError(msg, status.value());
    }
}
