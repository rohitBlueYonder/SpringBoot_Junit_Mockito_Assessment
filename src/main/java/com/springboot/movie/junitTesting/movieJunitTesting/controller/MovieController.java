package com.springboot.movie.junitTesting.movieJunitTesting.controller;

import com.springboot.movie.junitTesting.movieJunitTesting.exceptionHandling.IdException;
import com.springboot.movie.junitTesting.movieJunitTesting.exceptionHandling.MovieException;
import com.springboot.movie.junitTesting.movieJunitTesting.model.Movie;
import com.springboot.movie.junitTesting.movieJunitTesting.model.PayloadValidation;
import com.springboot.movie.junitTesting.movieJunitTesting.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService service){
        movieService = service;
    }

    // save movie
    @RequestMapping(value = "/add-movie", method = RequestMethod.POST)
    public Movie saveMovie(@RequestBody Movie payload)throws IdException {

        if(!PayloadValidation.createdPayloadValidation(payload))
            throw new IdException("Object id is not defined");

        return movieService.saveMovie(payload);
    }

    // get movie
    @RequestMapping(value = "/get-movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies(){
        return movieService.getMovies();
    }

    // get movie by id
    @RequestMapping(value = "/get-movie-by-id/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable("id") ObjectId id)throws MovieException {
        Movie movie = movieService.getMovieById(id);
        if(movie == null)
            throw new MovieException("Movie not exists!!");

        return movie;
    }

    //update Movie
    @RequestMapping(value = "/update-movie", method = RequestMethod.PATCH)
    public Movie updateMovie(@RequestBody Movie movie)throws MovieException{
        Movie movieResp = movieService.getMovieById(movie.getId());
        if(movieResp == null)
            throw new MovieException("Movie not exists");
        return movieService.updateMovie(movie);
    }

    // delete movie
    @RequestMapping(value = "/delete-movie-by-id/{id}", method = RequestMethod.DELETE)
    public String deleteMovieById(@PathVariable("id") ObjectId id) throws MovieException{
        Movie movie = movieService.getMovieById(id);
        if(movie == null)
            throw new MovieException("Movie not exists");

        movieService.deleteMovie(id);
        return "{\"message\" : \"Successfully Deleted !!\"}";
    }




}
