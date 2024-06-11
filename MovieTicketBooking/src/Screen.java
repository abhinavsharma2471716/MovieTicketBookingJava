import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Screen {
    private String screenType;
    private int pricePerTicket;
    private int totalSeats;
    private int availableSeats;
    private List<Booking> bookings;
    private Queue<Booking> waitingList;

    public Screen(String screenType, int pricePerTicket, int totalSeats) {
        this.screenType = screenType;
        this.pricePerTicket = pricePerTicket;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.bookings = new ArrayList<>();
        this.waitingList = new LinkedList<>();
    }

    public boolean bookSeat(Booking booking) {
        if (availableSeats > 0) {
            bookings.add(booking);
            availableSeats--;
            return true;
        } else {
            waitingList.add(booking);
            return false;
        }
    }

    public void cancelBooking(Booking booking) {
        if (bookings.remove(booking)) {
            availableSeats++;
            if (!waitingList.isEmpty()) {
                Booking waitingBooking = waitingList.poll();
                bookings.add(waitingBooking);
                availableSeats--;
            }
        }
    }

    public String getScreenType() {
        return screenType;
    }

    public int getPricePerTicket() {
        return pricePerTicket;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
