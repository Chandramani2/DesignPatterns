package service;

import lombok.AllArgsConstructor;
import model.Movie;
import model.Screen;
import model.Show;
import model.Theatre;
import repository.MovieRepository;
import repository.ShowRepository;
import repository.TheatreRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public Show createShow(String showId, String movieId, String theatreId, String screenId, Date startDate, Date endDate) {
        Movie movie = movieRepository.get(movieId);
        Theatre theatre = theatreRepository.get(theatreId);
        Screen screen = theatre.getScreen(theatreId);
        Show show = new Show(showId, movie, startDate, endDate, theatre, screen);
        showRepository.save(show);
        return show;
    }

    public Show getShow(String showId) {
        return showRepository.get(showId);
    }

    public List<Show> getShowsByMovieTitle(String movieTitle) {
        return showRepository.getAll().stream()
                .filter(show -> show.getMovie().getTitle().equalsIgnoreCase(movieTitle))
                .collect(Collectors.toUnmodifiableList());
    }
}
