package repository;

import model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> map = new HashMap<>();

    public void save(Movie movie){
        map.put(movie.getId(), movie);
    }

    public Movie get(String id){
        return map.get(id);
    }
}
