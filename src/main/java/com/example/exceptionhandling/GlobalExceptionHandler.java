package com.example.exceptionhandling;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> CustomerNotFoundException(CustomerNotFoundException ce)
    {
        return new ResponseEntity<>(ce.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> ResourceNotFoundException(ResourceNotFoundException re)
    {
        return new ResponseEntity<>(re.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handlingSQLException(SQLException se)
    {
        return new ResponseEntity<>("An Error occurred, Please Try again...!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlingNoSuchEle(NoSuchElementException nse)
    {
        return new ResponseEntity<>("Resource Not Found",HttpStatus.NOT_FOUND);
    }



    /*@ExceptionHandler(ConstraintViolationException.class)//it's raise when we add duplicate pair of product and custId in wishlist
    public ResponseEntity<String> handlingConstraintViolationException(ConstraintViolationException se)
    {
        return new ResponseEntity<>("Product Already in the Wishlist...!", HttpStatus.BAD_REQUEST);
    }*/

}
