package strategy.payment;

import model.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
