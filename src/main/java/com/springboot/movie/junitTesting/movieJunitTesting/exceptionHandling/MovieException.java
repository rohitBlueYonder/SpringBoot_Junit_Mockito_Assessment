package com.springboot.movie.junitTesting.movieJunitTesting.exceptionHandling;

public class MovieException extends Exception{
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public MovieException(){}

    public MovieException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
