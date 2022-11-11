package com.springboot.movie.junitTesting.movieJunitTesting.service;

import com.springboot.movie.junitTesting.movieJunitTesting.model.Movie;
import com.springboot.movie.junitTesting.movieJunitTesting.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {


    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    // create movie
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //get all movies
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    //get movie by id
    public Movie getMovieById(ObjectId id){
        Optional<Movie> checkMovie = movieRepository.findById(id);
        if(checkMovie.isPresent())
            return checkMovie.get();
        return null;
    }

    //get movie Update
    public Movie updateMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //delete movie
    public void deleteMovie(ObjectId id){
        movieRepository.deleteById(id);
    }
}
