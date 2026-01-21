package service;

import lombok.AllArgsConstructor;
import model.Movie;
import repository.MovieRepository;

@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    public Movie createMovie(String id, String title, int duration){
        Movie movie = new Movie(id, title, duration);
        movieRepository.save(movie);
        return movie;
    }

    public Movie getMovie(String id){
        return movieRepository.get(id);
    }
}
