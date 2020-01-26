package com.nitv.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

public class DeviceError {
    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;
    private List<DeviceValidationError> deviceValidationErrorList;

    public DeviceError() {
    }

    public DeviceError(String message, LocalDateTime timestamp, HttpStatus status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

   public void addValidationErrors(List<FieldError> fieldErrors){
       for (FieldError fieldError:fieldErrors) {
            DeviceValidationError error = new DeviceValidationError(fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage());
            deviceValidationErrorList.add(error);
       }
       setDeviceValidationErrorList(deviceValidationErrorList);
   }

    public void setDeviceValidationErrorList(List<DeviceValidationError> deviceValidationErrorList) {
        this.deviceValidationErrorList = deviceValidationErrorList;
    }
}
