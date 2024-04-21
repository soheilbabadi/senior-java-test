package ir.smartpath.senior.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.smartpath.senior.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.SocketTimeoutException;

@Slf4j
@ControllerAdvice
public class AdviceController {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        return handleExceptionInternal(e, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseBody
    public ResponseEntity<String> handleSocketTimeoutException(SocketTimeoutException e) {
        return handleExceptionInternal(e, HttpStatus.GATEWAY_TIMEOUT, "Request timed out: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return handleExceptionInternal(e, HttpStatus.NOT_FOUND, "Resource not found: " + e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseBody
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e) {
        return handleExceptionInternal(e, HttpStatus.NOT_ACCEPTABLE, "Error processing JSON: " + e.getMessage());
    }

    private ResponseEntity<String> handleExceptionInternal(Exception e, HttpStatus status, String message) {
        log.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(status).body(message);
    }

}