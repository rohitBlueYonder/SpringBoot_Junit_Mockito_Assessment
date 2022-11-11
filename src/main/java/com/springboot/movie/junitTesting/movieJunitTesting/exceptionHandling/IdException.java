package com.springboot.movie.junitTesting.movieJunitTesting.exceptionHandling;

public class IdException extends Exception{
    private String errorMessage;


    public IdException(){}

    public IdException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
