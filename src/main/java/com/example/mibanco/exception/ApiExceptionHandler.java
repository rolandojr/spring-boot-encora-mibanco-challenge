package com.example.mibanco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleApiException(ApiException apiException) {
        StandarizedApiExceptionResponse response = StandarizedApiExceptionResponse.builder()
                .type("UNCATEGORIZED")
                .title("Excepcion no controlada")
                .code(apiException.getCode())
                .detail(apiException.getMessage())
                .instance(apiException.getComponent())
                .build();
        return new ResponseEntity<>(response, apiException.getHttpStatus());
    }
}
