package strategy.payment;

import model.Booking;

public class UpiPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println("UpiPaymentStrategy paying");
        return true;
    }
}
