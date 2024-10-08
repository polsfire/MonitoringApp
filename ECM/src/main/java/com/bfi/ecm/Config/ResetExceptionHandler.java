package com.bfi.ecm.Config;

import com.bfi.ecm.DTO.ErrorDto;
import com.bfi.ecm.Exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResetExceptionHandler {
@ExceptionHandler(value={AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
    return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDto(ex.getMessage()));
}
}
