package service;

import lombok.AllArgsConstructor;
import model.Screen;
import model.Seat.Seat;
import model.Theatre;
import repository.TheatreRepository;

@AllArgsConstructor
public class TheatreService {

    private TheatreRepository theatreRepository;

    public Theatre createTheatre(String theatreId, String theatreName){
        Theatre theatre =  new Theatre(theatreId, theatreName);
        theatreRepository.save(theatre);
        return theatre;
    }

    public Theatre getTheatre(String theatreId){
        return theatreRepository.get(theatreId);
    }

    public void addScreen(String theatreId, Screen screen){
        Theatre theatre = getTheatre(theatreId);
        theatre.addScreen(screen);
        theatreRepository.save(theatre);
    }

    public void addSeat(String theatreId, String screenId, Seat seat){
        Theatre theatre = getTheatre(theatreId);
        Screen screen = theatre.getScreen(theatreId);
        screen.addSeat(seat);
        theatreRepository.save(theatre);
    }
}
