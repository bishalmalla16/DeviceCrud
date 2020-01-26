package com.nitv.error_handling;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DeviceErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = DeviceNotFoundException.class)
    public ResponseEntity<DeviceError> handleError(DeviceNotFoundException ex){
        DeviceError deviceError = new DeviceError();
        deviceError.setStatus(HttpStatus.NOT_FOUND);
        deviceError.setTimestamp(LocalDateTime.now());
        deviceError.setMessage(ex.getMessage());

        return new ResponseEntity<>(deviceError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DeviceError deviceError = new DeviceError();
        deviceError.setMessage(ex.getMessage());
        deviceError.setStatus(HttpStatus.BAD_REQUEST);
        deviceError.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(deviceError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        DeviceError deviceError = new DeviceError();
        deviceError.setMessage("Validation Error");
        deviceError.setStatus(HttpStatus.BAD_REQUEST);
        deviceError.setTimestamp(LocalDateTime.now());
        deviceError.addValidationErrors(ex.getBindingResult().getFieldErrors());

        return new ResponseEntity<>(deviceError, HttpStatus.BAD_REQUEST);
    }
}
