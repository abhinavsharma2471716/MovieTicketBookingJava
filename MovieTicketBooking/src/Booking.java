import java.time.LocalDateTime;

public class Booking {
    private Theater theater;
    private Screen screen;
    private int seatNumber;
    private LocalDateTime bookingTime;
    private boolean includesFood;
    private String foodItem;
    private double foodPrice;

    public Booking(Theater theater, Screen screen, int seatNumber, LocalDateTime bookingTime, boolean includesFood, String foodItem, double foodPrice) {
        this.theater = theater;
        this.screen = screen;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.includesFood = includesFood;
        this.foodItem = foodItem;
        this.foodPrice = foodPrice;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public boolean isIncludesFood() {
        return includesFood;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public double getFoodPrice() {
        return foodPrice;
    }
}
