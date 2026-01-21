package model.Seat;

import enums.SeatType;

public class ReclinerSeat extends Seat {
    public ReclinerSeat(String id, double price) {
        super(id, price);
    }

    public SeatType getSeatType() {
        return SeatType.RECLINER;
    }
}
