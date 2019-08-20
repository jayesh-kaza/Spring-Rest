package com.springrestcrm.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    //handles CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc)
    {
        CustomerErrorResponse cer = new CustomerErrorResponse();
        cer.setStatus(HttpStatus.NOT_FOUND.value());
        cer.setMessage(exc.getMessage());
        cer.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(cer,HttpStatus.NOT_FOUND);
    }

    //Handles all other Exceptions
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleExceptions(Exception ex)
    {
        CustomerErrorResponse response = new CustomerErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Bad request");
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


}
