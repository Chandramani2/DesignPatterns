package model.Seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Seat {
    private final String id;
    private double price;
}
