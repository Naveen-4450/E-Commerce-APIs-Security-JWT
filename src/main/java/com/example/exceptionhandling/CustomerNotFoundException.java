package com.example.exceptionhandling;

public class CustomerNotFoundException extends RuntimeException
{
    public CustomerNotFoundException(String msg)
    {
        super(msg);
    }
}
