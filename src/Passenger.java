import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);
    Admin infoAdmin;
    User[] passengerUser = new User[30];
    String string = new String();
    Boolean[] showFilterItems;
    String[][] passengerFlightDetail = new String[15][30];

    int i = 0;
    int j = 0;
    int number = 10;

    public void showPassengerMenu() {
        System.out.println("===========================");
        System.out.println("    PASSENGER MENU OPTIONS");
        System.out.println("===========================");
        System.out.println(". . . . . . . . . . . . . .");
        System.out.println("    <1> Change password");
        System.out.println("    <2> Search flight tickets");
        System.out.println("    <3> Booking ticket");
        System.out.println("    <4> Ticket cancellation");
        System.out.println("    <5> Booked tickets");
        System.out.println("    <6> Add charge");
        System.out.println("    <7> Show all flights");
        System.out.println("    <0> Sign out");
    }

    public void passengerOption() {
        while (number != 0) {
            number = 10;
            number = input.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Changing password...");
                    System.out.println("Enter your new password: ");
                    string = input.next();
                    passengerUser[i - 1].setPassword(string);
                    break;
                case 2:
                    System.out.println("Searching flight tickets...");
                    search();
                    break;
                case 3:
                    System.out.println("Booking tickets");
                    System.out.println("Would you like to see flight table once again? Y or N");
                    string = input.next();
                    if (string.equalsIgnoreCase("Y")) {
                        showAllFlights();
                    }
                    System.out.println("Enter your intended flight ID to book it.");
                    string = input.next();
                    if (isEnoughSeat(string)) {
                        if (isEnoughCharge(string)) {
                            bookTickets(string);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Cancelling...");
                    System.out.println("Would you like to see flight table once again? Y or N");
                    string = input.next();
                    if (string.equalsIgnoreCase("Y")) {
                        showAllFlights();
                    }
                    System.out.println("Enter your intended flight ID to cancel it.");
                    string = input.next();
                    ticketCancellation(string);
                    resetCharge(string);
                    resetSeat(string);

                    break;
                case 5:
                    System.out.println("Booked tickets...");
                    showBookedFlights();
                    break;
                case 6:
                    System.out.println("Adding charge...");
                    System.out.println("Enter the amount of money you want to charge");
                    int chargeAmount = input.nextInt();
                    charge(chargeAmount);
                    System.out.println("Your charge is: " + passengerUser[i].getCharge());
                    break;
                case 7:
                    System.out.println("All flights:");
                    showAllFlights();
                case 0:
                    break;
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }

    public void bookTickets(String string) {
        int flag = 0;
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                isAllowToChange(k);
                passengerFlightDetail[i][j] = infoAdmin.flights[k].getFlightId();
                System.out.println("Your ticket was successfully reserved");
                updateSeat(string);
                j++;
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("This flight doesn't exist");
        }
    }

    public void ticketCancellation(String string) {
        int flag = 0;
        for (int j = 0; j < 15; j++) {
            if (passengerFlightDetail[i][j] != null && passengerFlightDetail[i][j].equals(string)) {
                passengerFlightDetail[i][j] = null;
                System.out.println("Your flight is canceled");
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("You haven't register this flight before.");
        }
    }

    public void showBookedFlights() {
        for (int j = 0; j < 15; j++) {
            if (passengerFlightDetail[i][j] != null) {
                System.out.println(passengerFlightDetail[i][j]);
            }
        }
    }

    public boolean isEnoughCharge(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                if (passengerUser[i - 1].getCharge() >= infoAdmin.flights[k].getPrice()) {
                    return true;
                } else {
                    while (true) {
                        System.out.println("Your charge is not enough -> If you want to charge enter 'Y' to charge your account");
                        string = input.next();
                        if (string.equalsIgnoreCase("Y")) {
                            System.out.println("Enter the amount of money you want to charge");
                            int chargeAmount = input.nextInt();
                            charge(chargeAmount);
                            System.out.println("You added " + passengerUser[i - 1].getCharge() + " to your wallet.");
                            if (passengerUser[i - 1].getCharge() >= infoAdmin.flights[k].getPrice()) {
                                passengerUser[i - 1].setCharge(((passengerUser[i - 1].getCharge()) + (infoAdmin.flights[k].getPrice() * (-1))));
                                System.out.println("Now your charge is: " + passengerUser[i - 1].getCharge());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isEnoughSeat(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getSeats() > 0) {
                return true;
            }
        }
        return false;
    }
    public void updateSeat(String string){
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)){
                infoAdmin.flights[k].setSeats(infoAdmin.flights[k].getSeats() - 1);
            }
        }
    }

    public void resetCharge(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                passengerUser[i - 1].setCharge(passengerUser[i - 1].getCharge() + infoAdmin.flights[k].getPrice());
            }
        }
    }

    public void resetSeat(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                infoAdmin.flights[k].setSeats(infoAdmin.flights[k].getSeats() + 1);
            }
        }

    }

    public void isAllowToChange(int number) {
        infoAdmin.flights[number].setAllow(false);
    }

    public void registration(String userName, String password) {
        passengerUser[i] = new User();
        passengerUser[i].setUserName(userName);
        passengerUser[i].setPassword(password);
        passengerFlightDetail[i][15] = new String();
        i++;
        j = 0;
    }

    public void startFilter() {
        showFilterItems = new Boolean[infoAdmin.flights.length];
        for (int i = 0; i < showFilterItems.length; i++) {
            showFilterItems[i] = false;
        }
    }

    public void filterOriginMixed() {
        System.out.println("Filtering Origin (Press N to escape)");
        string = input.next();

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if ((infoAdmin.flights[i] != null) && (infoAdmin.flights[i].getOrigin().equals(string)) && (!string.equalsIgnoreCase("N"))) {
                showFilterItems[i] = true;
            }
        }
    }

    public void filterDestinationMixed() {
        System.out.println("Filtering Destination (Press N to escape)");
        string = input.next();

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if ((infoAdmin.flights[i] != null) && (infoAdmin.flights[i].getDestination().equals(string)) && (!string.equalsIgnoreCase("N"))) {
                showFilterItems[i] = true;
            }
        }
    }

    public void filterPriceMixed() {
        System.out.println("Filtering Price (Press N to escape)");
        string = input.next();

        if (!string.equalsIgnoreCase("N")) {

            System.out.println("============Defining price range============");
            System.out.println("Enter the first range:");
            int x = input.nextInt();
            System.out.println("Enter the second range:");
            int y = input.nextInt();
            for (int i = 0; i < infoAdmin.flights.length; i++) {
                if (infoAdmin.flights[i] != null) {
                    if (checkValueBetween(x, y, i)) {
                        showFilterItems[i] = true;
                    }
                }
            }
        }
    }

    public void printFilters() {
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (showFilterItems[i].equals(true)) {
                printFlight(i);
            }
        }
    }

    public void resetFilters() {
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            showFilterItems[i] = true;
        }
    }

    public boolean checkValueBetween(int x, int y, int i) {
        if (infoAdmin.flights[i].getPrice() >= x && infoAdmin.flights[i].getPrice() <= y) {
            return true;
        }
        return false;
    }

    public void charge(int chargeAmount) {
        passengerUser[i - 1].setCharge(chargeAmount);
    }


    public void search() {
        System.out.println(".............How do you want to filter flights?.............\n" +
                "   <1> Filter by one item\n" +
                "   <2> Filter by some items\n");
        number = input.nextInt();
        switch (number) {
            case 1:
                System.out.println("Filtering one item...");
                showFilterMenu();
                number = input.nextInt();
                switch (number) {
                    case 1:
                        System.out.println("Filter by origin:");
                        filterOrigin();
                        break;
                    case 2:
                        System.out.println("Filter by destination:");
                        filterDestination();
                        break;
                    case 3:
                        System.out.println("Filter by date:");
                        filterDate();
                        break;
                    case 4:
                        System.out.println("Filter by time:");
                        filterTime();
                        break;
                    case 5:
                        System.out.println("Filter by price:");
                        filterPrice();
                }
                break;
            default:
                System.out.println("Incorrect input!!!");
                break;

            case 2:
                System.out.println("Filtering by some items");
                startFilter();
                filterOriginMixed();
                filterDestinationMixed();
                filterPriceMixed();
                printFilters();
                resetFilters();
        }
    }

    public void filterOrigin() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null && infoAdmin.flights[i].getOrigin().equals(string)) {
                printFlight(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Your intended flight is not available");
        }
    }

    public void filterDestination() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null && infoAdmin.flights[i].getDestination().equals(string)) {
                printFlight(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Your intended flight is not available");
        }
    }

    public void filterDate() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null && infoAdmin.flights[i].getDate().equals(string)) {
                printFlight(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Your intended flight is not available");
        }
    }

    public void filterTime() {
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null && infoAdmin.flights[i].getTime().equals(string)) {
                printFlight(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Your intended flight is not available");
        }
    }

    public void filterPrice() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null && infoAdmin.flights[i].getPrice() == Integer.parseInt(string)) {
                printFlight(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Your intended flight is not available");
        }
    }

    public void showFilterMenu() {
        System.out.println("    <1> Filter by Origin");
        System.out.println("    <2> Filter by Destination");
        System.out.println("    <3> Filter by Date");
        System.out.println("    <4> Filter by Time");
        System.out.println("    <5> Filter by Price");
    }

    public void printFlight(int i) {
        System.out.println(".....................................................................................................");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", infoAdmin.flights[i].getFlightId(), infoAdmin.flights[i].getOrigin(), infoAdmin.flights[i].getDestination(), infoAdmin.flights[i].getDate(), infoAdmin.flights[i].getTime(), infoAdmin.flights[i].getPrice(), infoAdmin.flights[i].getSeats());
    }

    public void showAllFlights() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (infoAdmin.flights[i] != null) {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", infoAdmin.flights[i].getFlightId(), infoAdmin.flights[i].getOrigin(), infoAdmin.flights[i].getDestination(), infoAdmin.flights[i].getDate(), infoAdmin.flights[i].getTime(), infoAdmin.flights[i].getPrice(), infoAdmin.flights[i].getSeats());
                System.out.println(".....................................................................................................");
            }
        }
    }
}
