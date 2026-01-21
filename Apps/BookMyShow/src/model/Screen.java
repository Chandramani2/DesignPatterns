package model;

import lombok.Getter;
import model.Seat.Seat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Screen {
    private final String id;
    private final Map<String, Seat> seats = new HashMap<>();


    public Screen(String id) {
        this.id = id;
    }

    public void addSeat(Seat seat) {
        seats.put(id, seat);
    }

    public List<Seat> getSeats() {
        return new ArrayList<>(seats.values());
    }
}
