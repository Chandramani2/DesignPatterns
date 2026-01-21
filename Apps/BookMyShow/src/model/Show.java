package model;

import lombok.Getter;
import model.Seat.Seat;

import java.util.Date;
import java.util.List;

@Getter
public class Show {
    private final String id;
    private final Movie movie;
    private final Date startDate;
    private final Date endDate;
    private final Theatre theatre;
    private final Screen screen;

    public Show(String id, Movie movie, Date startDate, Date endDate, Theatre theatre, Screen screen) {
        this.id = id;
        this.movie = movie;
        this.startDate = startDate;
        this.endDate = endDate;
        this.theatre = theatre;
        this.screen = screen;
    }

    public List<Seat> getSeats() {
        return screen.getSeats();
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", movie=" + movie +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", theatre=" + theatre +
                ", screen=" + screen +
                '}';
    }
}
