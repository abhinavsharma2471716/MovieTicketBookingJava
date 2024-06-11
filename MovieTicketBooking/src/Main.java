import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Theater theater = new Theater("PVR Cinemas", "Mumbai");
        Screen goldScreen = new Screen("Gold", 400, 2);
        Screen imaxScreen = new Screen("IMAX", 300, 5);
        Screen generalScreen = new Screen("General", 200, 10);

        theater.addScreen(goldScreen);
        theater.addScreen(imaxScreen);
        theater.addScreen(generalScreen);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookTicket(scanner, theater);
                    break;
                case 2:
                    cancelBooking(scanner, theater);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private static void bookTicket(Scanner scanner, Theater theater) {
        System.out.println("Select Theater:");
        System.out.println("1. " + theater.getName());
        int theaterChoice = scanner.nextInt();

        System.out.println("Select Screen Type:");
        System.out.println("1. Gold");
        System.out.println("2. IMAX");
        System.out.println("3. General");
        int screenChoice = scanner.nextInt();

        Screen selectedScreen = null;
        switch (screenChoice) {
            case 1:
                selectedScreen = theater.getScreens().get(0);
                break;
            case 2:
                selectedScreen = theater.getScreens().get(1);
                break;
            case 3:
                selectedScreen = theater.getScreens().get(2);
                break;
        }

        if (selectedScreen != null) {
            System.out.println("Do you want to add food? (yes/no)");
            boolean includesFood = scanner.next().equalsIgnoreCase("yes");
            String foodItem = "";
            double foodPrice = 0;
            if (includesFood) {
                System.out.println("Select Food Item:");
                System.out.println("1. Popcorn - Rs. 100");
                System.out.println("2. Sandwich - Rs. 150");
                int foodChoice = scanner.nextInt();
                switch (foodChoice) {
                    case 1:
                        foodItem = "Popcorn";
                        foodPrice = 100;
                        break;
                    case 2:
                        foodItem = "Sandwich";
                        foodPrice = 150;
                        break;
                }

                if (selectedScreen.getScreenType().equals("Gold")) {
                    foodPrice *= 0.9;
                } else if (selectedScreen.getScreenType().equals("IMAX")) {
                    foodPrice *= 0.95;
                }
            }

            Booking booking = new Booking(theater, selectedScreen, selectedScreen.getTotalSeats() - selectedScreen.getAvailableSeats() + 1, LocalDateTime.now(), includesFood, foodItem, foodPrice);
            boolean success = selectedScreen.bookSeat(booking);
            if (success) {
                System.out.println("Booking successful!");
            } else {
                System.out.println("Screen full. Added to waiting list.");
            }
        }
    }

    private static void cancelBooking(Scanner scanner, Theater theater) {
        System.out.println("Enter Screen Type:");
        System.out.println("1. Gold");
        System.out.println("2. IMAX");
        System.out.println("3. General");
        int screenChoice = scanner.nextInt();

        Screen selectedScreen = null;
        switch (screenChoice) {
            case 1:
                selectedScreen = theater.getScreens().get(0);
                break;
            case 2:
                selectedScreen = theater.getScreens().get(1);
                break;
            case 3:
                selectedScreen = theater.getScreens().get(2);
                break;
        }

        System.out.println("Enter Seat Number to Cancel:");
        int seatNumber = scanner.nextInt();

        Booking bookingToCancel = null;
        for (Booking booking : selectedScreen.getBookings()) {
            if (booking.getSeatNumber() == seatNumber) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            selectedScreen.cancelBooking(bookingToCancel);
            System.out.println("Booking cancelled successfully!");
        } else {
            System.out.println("Booking not found.");
        }
    }
}
