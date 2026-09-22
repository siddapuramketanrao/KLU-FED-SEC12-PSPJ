import java.util.Scanner;

class IntercityBusReservation {

    // Method to display available buses
    static void displayBuses(String[] busNames, String[] routes, double[] fares) {
        System.out.println("\n========== AVAILABLE BUSES ==========");

        for (int i = 0; i < busNames.length; i++) {
            System.out.println((i + 1) + ". " + busNames[i]
                    + " | Route: " + routes[i]
                    + " | Fare: Rs." + fares[i]);
        }
    }

    // Method to display seat layout
    static void displaySeats(int[][] seats) {
        System.out.println("\n========== SEAT LAYOUT ==========");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {

                if (seats[i][j] == 0)
                    System.out.print("[A] ");
                else
                    System.out.print("[X] ");
            }
            System.out.println();
        }

        System.out.println("A = Available   X = Reserved");
    }

    // Method to count available seats
    static int availableSeats(int[][] seats) {
        int count = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    // Method to calculate fare
    static double calculateFare(double fare, int passengers) {
        double total = fare * passengers;

        // Discount using conditional statement
        if (passengers >= 4) {
            total = total - (total * 0.10);
        }

        return total;
    }

    // Method to reserve seats
    static void reserveSeats(int[][] seats, Scanner sc, int passengers) {

        for (int p = 1; p <= passengers; p++) {

            boolean validSeat = false;

            while (!validSeat) {

                System.out.print("Enter seat row (1-5) for passenger "
                        + p + ": ");
                int row = sc.nextInt();

                System.out.print("Enter seat column (1-4): ");
                int column = sc.nextInt();

                // Conditional operators
                if (row >= 1 && row <= 5 &&
                    column >= 1 && column <= 4) {

                    if (seats[row - 1][column - 1] == 0) {

                        seats[row - 1][column - 1] = 1;
                        System.out.println("Seat reserved successfully!");
                        validSeat = true;

                    } else {
                        System.out.println("Seat already reserved!");
                    }

                } else {
                    System.out.println("Invalid seat number!");
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Data types
        int choice;
        int busChoice;
        int passengers;
        double totalFare;
        String name;

        // 1D Arrays
        String[] busNames = {
            "Express 101",
            "Super Fast 202",
            "City Rider 303"
        };

        String[] routes = {
            "Hyderabad - Vijayawada",
            "Hyderabad - Bangalore",
            "Hyderabad - Chennai"
        };

        double[] fares = {
            500.0,
            800.0,
            900.0
        };

        // 2D Array
        // 0 = Available
        // 1 = Reserved
        int[][] seats = new int[5][4];

        System.out.println("========================================");
        System.out.println("      INTERCITY BUS RESERVATION SYSTEM");
        System.out.println("========================================");

        System.out.print("Enter passenger name: ");
        name = sc.nextLine();

        // Iterative statement
        boolean running = true;

        while (running) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Display Buses");
            System.out.println("2. Display Seat Layout");
            System.out.println("3. Book Ticket");
            System.out.println("4. Check Available Seats");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            // Switch-case
            switch (choice) {

                case 1:
                    displayBuses(busNames, routes, fares);
                    break;

                case 2:
                    displaySeats(seats);
                    break;

                case 3:

                    displayBuses(busNames, routes, fares);

                    System.out.print("\nSelect bus: ");
                    busChoice = sc.nextInt();

                    // Conditional statement
                    if (busChoice < 1 || busChoice > busNames.length) {
                        System.out.println("Invalid bus choice!");
                        break;
                    }

                    System.out.print("Enter number of passengers: ");
                    passengers = sc.nextInt();

                    // Operators
                    if (passengers <= 0) {
                        System.out.println("Number of passengers must be greater than 0.");
                        break;
                    }

                    // Check seat availability
                    int available = availableSeats(seats);

                    if (passengers > available) {
                        System.out.println("Only " + available
                                + " seats are available.");
                        break;
                    }

                    // Reserve seats
                    reserveSeats(seats, sc, passengers);

                    // Calculate total fare
                    totalFare = calculateFare(
                            fares[busChoice - 1],
                            passengers
                    );

                    System.out.println("\n========== TICKET ==========");
                    System.out.println("Passenger Name : " + name);
                    System.out.println("Bus            : " + busNames[busChoice - 1]);
                    System.out.println("Route          : " + routes[busChoice - 1]);
                    System.out.println("Passengers     : " + passengers);
                    System.out.println("Total Fare     : Rs." + totalFare);

                    if (passengers >= 4) {
                        System.out.println("Discount       : 10%");
                    } else {
                        System.out.println("Discount       : None");
                    }

                    System.out.println("Booking Status : CONFIRMED");
                    break;

                case 4:
                    System.out.println("\nAvailable seats: "
                            + availableSeats(seats));
                    break;

                case 5:
                    System.out.println("\nThank you for using the");
                    System.out.println("Intercity Bus Reservation System!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}