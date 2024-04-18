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

        log.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseBody
    public ResponseEntity<String> handleSocketTimeoutException(SocketTimeoutException e) {
        log.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Request timed out: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        log.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Request timed out: " + e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseBody
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException e) {
        log.error("An error occurred: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("Request timed out: " + e.getMessage());
    }
}

