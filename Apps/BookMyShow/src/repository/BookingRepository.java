package repository;

import model.Booking;
import model.Movie;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> map = new HashMap<>();

    public void save(Booking booking){
        map.put(booking.getId(), booking);
    }

    public Booking get(String id){
        return map.get(id);
    }
}
