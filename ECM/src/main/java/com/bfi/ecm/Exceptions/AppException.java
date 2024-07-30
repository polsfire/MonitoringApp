package com.bfi.ecm.Exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

    private final HttpStatus httpStatus;
    public  AppException(String message, HttpStatus httpssatatus) {
super(message);
this.httpStatus=httpssatatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}

//advice configuration to intercept the exeception
