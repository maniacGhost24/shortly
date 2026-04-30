package com.shortly.shortly.exception;

public class UrlNotFoundException extends RuntimeException {

    public UrlNotFoundException(String message){
        super(message);
    }
    
}
