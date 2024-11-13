package com.app.jagarv.exception.exceptions.users;

public class UserNotFoundException extends RuntimeException 
{
    public UserNotFoundException(String e)
    {
        super(e);
    }
}