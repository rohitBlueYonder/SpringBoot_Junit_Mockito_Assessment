package com.springboot.movie.junitTesting.movieJunitTesting.service;

import com.springboot.movie.junitTesting.movieJunitTesting.model.Movie;
import com.springboot.movie.junitTesting.movieJunitTesting.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    // dummy data
    @Mock
    private MovieRepository repository;

    // dummy service for injecting data
    @InjectMocks
    private MovieService service;


    // before each test case, ready the mocked data
    @Before
    public void setup(){

        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllMovies(){
        List<Movie> employeeList = new ArrayList<Movie>();
        employeeList.add(new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10"));
        employeeList.add(new Movie(new ObjectId("636dcf444b7e8832baeb2608"), "MI2", "2002-10-10"));
        employeeList.add(new Movie(new ObjectId("636dcf444b7e8832baeb2609"), "MI3", "2002-02-20"));

        when(repository.findAll()).thenReturn(employeeList);

        List<Movie> employeesResult = service.getMovies();

        assertEquals(3, employeesResult.size());
    }

    @Test
    public void getMovieId(){

        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        when(repository.findById(new ObjectId("636dcf444b7e8832baeb2607"))).thenReturn(Optional.of(movie));

        Movie savedMovie = service.getMovieById(new ObjectId("636dcf444b7e8832baeb2607"));

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedMovie.getId());
        assertEquals("MI1", savedMovie.getName());
        assertEquals("2002-10-10", savedMovie.getReleaseDate());

    }

    @Test
    public void saveMovie(){

        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        when(repository.save(movie)).thenReturn(movie);

        Movie savedMovie = service.saveMovie(movie);

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedMovie.getId());
        assertEquals("MI1", savedMovie.getName());
        assertEquals("2002-10-10", savedMovie.getReleaseDate());
    }


    @Test
    public void deleteMovieById(){

        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        service.deleteMovie(movie.getId());

        verify(repository, times(1)).deleteById(movie.getId());
    }

}
