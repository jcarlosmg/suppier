package com.metalsa.supplier.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Unauthorized.class)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> resourceUnauthorizedException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstaintViolatoinException(ConstraintViolationException ex, WebRequest request) {

      StringBuilder message = new StringBuilder();
      Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
      for (ConstraintViolation<?> violation : violations) {
    	  
        message.append(violation.getInvalidValue().toString().concat(";"));
      }
      ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
      LOG.info(message.toString());
//      return new ResponseObject(HttpStatus.PRECONDITION_FAILED.value(), message.toString());
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
    	String msj = ex.getMessage().replaceAll("com.metalsa.", "");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), msj, request.getDescription(false));
        LOG.error(msj);
     // printStackTrace method 
        // prints line numbers + call stack 
        ex.printStackTrace(); 
          
        // Prints what exception has been thrown 
        System.out.println(ex); 
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}