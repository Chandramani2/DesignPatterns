package model;

import enums.BookingStatus;
import enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
    private final String id;
    private final String userId;
    private String showId;
    private final List<String> seatIds;
    private BookingStatus bookingStatus;
    private PaymentType paymentType;
    private double amount;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", showId='" + showId + '\'' +
                ", seatIds=" + seatIds +
                ", bookingStatus=" + bookingStatus +
                ", paymentType=" + paymentType +
                ", amount=" + amount +
                '}';
    }
}
