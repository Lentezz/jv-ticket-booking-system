package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int seatsAvailable;
    private final Semaphore seatsAvailableSemaphore;

    public TicketBookingSystem(int totalSeats) {
        seatsAvailable = totalSeats;
        seatsAvailableSemaphore = new Semaphore(1);
    }

    public BookingResult attemptBooking(String user) {
        try {
            seatsAvailableSemaphore.acquire();
            if (seatsAvailable != 0) {
                seatsAvailable--;
                return new BookingResult(user, true, "Booking successful.");
            }
            return new BookingResult(user, false, "No seats available.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            seatsAvailableSemaphore.release();
        }
    }
}
