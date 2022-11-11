package com.springboot.movie.junitTesting.movieJunitTesting.model;

public class PayloadValidation {


    public static boolean createdPayloadValidation(Movie movie){


        if(movie.getId()!=null){
            return false;
        }

        return true;
    }
}
