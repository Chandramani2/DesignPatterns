package service;

import enums.BookingStatus;
import enums.PaymentType;
import exception.SeatNotAvailableException;
import lombok.AllArgsConstructor;
import model.Booking;
import model.Seat.Seat;
import model.Show;
import repository.BookingRepository;
import strategy.locking.LockProvider;
import strategy.payment.PaymentStrategy;
import strategy.payment.PaymentStrategyFactory;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class BookingService {

    private final LockProvider lockProvider;
    private final BookingRepository bookingRepo;

    private static final long TTL = 5000L;

    public Booking createBooking(String userId, Show show, List<String> seatIds){

        for(String seatId : seatIds){
            String key = show.getId() + ":" + seatId;
            if(!lockProvider.tryLock(key, userId, TTL)){
                throw new SeatNotAvailableException("Seat "+ seatId + " is temporarily unavailable");
            }
        }

        double totalPrice = 0.0;
        for(Seat seat : show.getSeats()){
            if(seatIds.contains(seat.getId())){
                totalPrice += seat.getPrice();
            }
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                userId,
                show.getId(),
                seatIds,
                BookingStatus.CREATED,
                null,
                totalPrice
        );

        bookingRepo.save(booking);
        System.out.println("Booking Created: " + booking.getId());
        return booking;
    }

    public void confirmBooking(Booking booking, PaymentType paymentType){

        if(booking.getBookingStatus()!= BookingStatus.CREATED){
            throw new IllegalStateException("Booking status is not in valid state for creation");
        }

        for (String seatId: booking.getSeatIds()){
            String key = booking.getShowId() + ":" + seatId;
            if(lockProvider.isLockExpired(key) || !lockProvider.isLockedBy(key, booking.getUserId())){
                throw new SeatNotAvailableException("Seat "+ seatId + " is unavailable");
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(booking.getPaymentType());
        paymentStrategy.pay(booking);

        for(String seatId: booking.getSeatIds()){
            String key = booking.getShowId() + ":" + seatId;
            lockProvider.unlock(key);
        }

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking Confirmed: " + booking.getId());
    }
}
