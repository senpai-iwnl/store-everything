package com.example.storyeverything.exception;

public class DuplicateLoginException extends RuntimeException{
    public DuplicateLoginException(String message){
        super(message);
    }
}
