package com.example.mibanco.util.exception;

import com.example.mibanco.exception.ApiException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

@Component
@Slf4j
public class ExceptionBuilder {


    public ApiException buildExternalException(Throwable exception, String component) {
        var builder = ApiException.builder();
        if (exception instanceof SocketTimeoutException) {
            buildException((Exception) exception, HttpStatus.SERVICE_UNAVAILABLE, builder, "T-101", component);
        }
        if (exception instanceof UnknownHostException) {
            buildException((Exception) exception, HttpStatus.SERVICE_UNAVAILABLE, builder, "T-102", component);
        }
        if (exception instanceof CallNotPermittedException) {
            buildException((Exception) exception, HttpStatus.SERVICE_UNAVAILABLE, builder, "T-103", component);
        }
        if (exception instanceof RequestNotPermitted) {
            buildException((Exception) exception, HttpStatus.SERVICE_UNAVAILABLE, builder, "T-104", component);
        } else {
            buildException((Exception) exception, HttpStatus.INTERNAL_SERVER_ERROR, builder, "T-106", component);
        }
        return builder.build();

    }

    public void buildException(Exception exception, HttpStatus httpStatus,
                               ApiException.ApiExceptionBuilder builder, String code,
                               String component) {
        builder.httpStatus(httpStatus)
                .code(code)
                .message(exception.getMessage())
                .component(component)
                .build();
    }
}
