package com.example.mibanco.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private String component;

    public ApiException(HttpStatus httpStatus, String code, String message, String component) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.component = component;
    }

}
