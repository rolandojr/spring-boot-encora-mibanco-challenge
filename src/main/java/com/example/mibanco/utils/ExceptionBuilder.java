package com.example.mibanco.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionBuilder extends RuntimeException{

    private String code;
    private String message;

}
