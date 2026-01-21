//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import enums.PaymentType;
import model.*;
import model.Seat.ReclinerSeat;
import model.Seat.RegularSeat;
import repository.BookingRepository;
import repository.MovieRepository;
import repository.ShowRepository;
import repository.TheatreRepository;
import service.BookingService;
import service.MovieService;
import service.ShowService;
import service.TheatreService;
import strategy.locking.InMemoryLockProvider;
import strategy.locking.LockProvider;

public static void main(String[] args) {

    TheatreRepository theatreRepository = new TheatreRepository();
    MovieRepository movieRepository = new MovieRepository();
    ShowRepository showRepository = new ShowRepository();
    BookingRepository bookingRepository = new BookingRepository();

    LockProvider lockProvider = new InMemoryLockProvider();

    // Services
    TheatreService theatreService = new TheatreService(theatreRepository);
    MovieService movieService = new MovieService(movieRepository);
    ShowService showService = new ShowService(showRepository, movieRepository, theatreRepository);
    BookingService bookingService = new BookingService(lockProvider, bookingRepository);

    // Creating Theatre and Screen
    Theatre pvr = theatreService.createTheatre("t1", "PVR Phoenix");
    Screen screen1 = new Screen("s1");
    theatreService.addScreen("t1", screen1);

    // Regular Seats
    theatreService.addSeat("t1","s1", new RegularSeat("s1-2", 150.00));
    theatreService.addSeat("t1","s1", new RegularSeat("s1-3", 150.00));
    theatreService.addSeat("t1","s1", new RegularSeat("s1-4", 150.00));
    theatreService.addSeat("t1","s1", new RegularSeat("s1-5", 150.00));
    theatreService.addSeat("t1","s1", new RegularSeat("s1-6", 150.00));
    theatreService.addSeat("t1","s1", new RegularSeat("s1-7", 150.00));

    // Recliner Seats
    theatreService.addSeat("t1","s1", new ReclinerSeat("s1-6", 150.00));
    theatreService.addSeat("t1","s1", new ReclinerSeat("s1-7", 150.00));


    //Create Movie
    Movie movie = movieService.createMovie("m1", "Interstellar", 180);

    // Schedule show
    Calendar cal = Calendar.getInstance();
    cal.set(2026, Calendar.JANUARY, 22, 18, 30, 0);
    Date showStartTime = cal.getTime();

    Show show1 = showService.createShow("show1", "m1", "t1", "s1", showStartTime,showStartTime);

    System.out.println("=========== 1. Search Shows for Movie ==========");
    List<Show> shows = showService.getShowsByMovieTitle("Interstellar");
    shows.forEach(System.out::println);

    System.out.println("========= 2. User Book Seats ========");
    Booking  booking1 = bookingService.createBooking("user1", show1, List.of("s1-1", "s1-2"));
    bookingService.confirmBooking(booking1, PaymentType.CARD);

    System.out.println("========= 3. Concurrent Bookings ========");
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.submit(()->{
        try{
            Booking b = bookingService.createBooking("user2", show1, List.of("s1-3", "s1-4"));
            Thread.sleep(1000);
            bookingService.confirmBooking(b, PaymentType.CARD);
        } catch (Exception e){
            System.out.println("user2 failed " + e.getMessage());
        }
    });

    executor.submit(()->{
        try{
            Booking b = bookingService.createBooking("user3", show1, List.of("s1-4", "s1-5"));
            Thread.sleep(1000);
            bookingService.confirmBooking(b, PaymentType.CARD);
        } catch (Exception e){
            System.out.println("user3 failed " + e.getMessage());
        }
    });

    System.out.println("========= 4. User Book Seats after TTL ========");

    try{
        Booking b = bookingService.createBooking("user4", show1, List.of("s1-6", "s1-7"));
        System.out.println("user4 created booking but did not pay within TTL");
        // TTL - 5sec

        Thread.sleep(6000);

        System.out.println("user5 trying to book the same seats after TTL...");
        Booking b2 = bookingService.createBooking("user5", show1, List.of("s1-6", "s1-7"));
        System.out.println("user5 created booking");

        try{
            System.out.println("user4 trying to pay after TTL...");
            bookingService.confirmBooking(b, PaymentType.CARD);
        }catch(Exception e){
            System.out.println("user4 payment failed " + e.getMessage());
        }

        try{
            System.out.println("user5 confirming payment...");
            bookingService.confirmBooking(b2, PaymentType.CARD);
            System.out.println("user5 payment successfull...");
        }catch(Exception e){
            System.out.println("user5 payment failed " + e.getMessage());
        }
    } catch (Exception e) {
        System.out.println("Test Case 4 failed " + e.getMessage());
    }

}
