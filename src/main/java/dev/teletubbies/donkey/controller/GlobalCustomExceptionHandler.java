package dev.teletubbies.donkey.controller;

import dev.teletubbies.donkey.exception.CustomException;
import dev.teletubbies.donkey.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<Void>> baseExceptionHandler(CustomException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(new CommonResponse<>(exception.getMessage(), null));
    }
}
