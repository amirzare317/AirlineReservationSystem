import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);

    // By creating this instance, you are able to access to the feature of Admin class.
    Admin infoAdmin;
    // In this array the infos of each passenger will be saved. Note that you can have at most 30 passengers.
    User[] passengerUser = new User[30];
    String string = "";
    String str = "";

    // This array is used to show filtering. Note that the maxim length of array is equal to length of flights.
    Boolean[] showFilterItems;

    // This matrix is created for getting all the information of passengers(likely a local database).
    // Each row is known as a passenger.
    // In each column the data of each passenger including the tickets will be stored.
    String[][] passengerFlightDetail = new String[15][30];

    // i is the identifier of users.
    int i = 0;

    // lineOrder makes you available to go throw each row of matrix.
    int lineOrder = 0;

    // Because in Java the integers are initialized by zero as default, number is initialized by 10 to avoid interference.
    int number = 10;

    /**
     * Show the Menu of Passenger.
     */
    public void showPassengerMenu() {
        System.out.println("==============================");
        System.out.println("    PASSENGER MENU OPTIONS");
        System.out.println("==============================");
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

    /**
     * When the menu showed, by entering any button which is designed here, the program will start to run.
     */
    public void passengerOption() {
        number = 10;
        while (number != 0) {
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
                    System.out.println("This is all the flights you have reserved before...");
                    for (int j = 0; j < infoAdmin.flights.length; j++) {
                        if (passengerFlightDetail[i - 1][j] != null) {
                            System.out.println("Your reservation code is: " + passengerFlightDetail[i - 1][j]);
                        }
                    }
                    System.out.println("Enter your intended flight ID to cancel it.");
                    str = input.next();
                    System.out.println("Enter your reservation code to cancel it.");
                    string = input.next();
                    ticketCancellation(string);
                    resetCharge(string);
                    resetSeat(string);
                    resetAllow(str);

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
                    System.out.println("Your charge is: " + passengerUser[i - 1].getCharge());
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

    /**
     * Passenger can book a ticket.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     */
    public void bookTickets(String string) {
        int flag = 0;
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {

                // If the condition above is true, the ticketID will save to the related index of matrix.
                // The structure of ticketID is: FlightID + Date + # + Free seats.
                passengerFlightDetail[i - 1][lineOrder] = infoAdmin.flights[k].getFlightId() + infoAdmin.flights[k].getDate() + "#" + infoAdmin.flights[k].getSeats();

                // Now the passenger bought the ticket, so that the admin doesn't allow to update or delete that flight.
                isAllowToChange(k);
                System.out.println("Your ticket was successfully reserved");

                // Since a passenger has bought a ticket, so update the number of seats.
                updateSeat(string);
                lineOrder++;
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("This flight doesn't exist");
        }
    }

    /**
     * Passenger can cancel a ticket.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     *               By cancelling, the ticketID will initialize to null.
     */
    public void ticketCancellation(String string) {
        int flag = 0;
        for (int j = 0; j < 15; j++) {
            if (passengerFlightDetail[i - 1][j] != null && passengerFlightDetail[i - 1][j].equals(string)) {
                passengerFlightDetail[i - 1][j] = null;
                System.out.println("Your flight is canceled");
                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("You haven't register this flight before.");
        }
    }

    /**
     * This method shows all the flights that has been reserved before.
     * It will search in matrix and if ticketID contains FlightID, will represent the flight info.
     */
    public void showBookedFlights() {
        for (int j = 0; j < infoAdmin.flights.length; j++) {
            if (passengerFlightDetail[i - 1][j] != null) {
                for (int k = 0; k < infoAdmin.flights.length; k++) {
                    if ((infoAdmin.flights[k] != null) && (passengerFlightDetail[i - 1][j].contains(infoAdmin.flights[k].getFlightId()))) {
                        printFlight(k);
                    }
                }
            }
        }
    }

    /**
     * To check if passenger have enough cash before buying ticket.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     * @return If passenger have enough charge, it returns true otherwise it return false.
     * This loop repeats until passenger have enough charge to book his/her intended flight.
     */
    public boolean isEnoughCharge(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                if (passengerUser[i - 1].getCharge() >= infoAdmin.flights[k].getPrice()) {
                    return true;
                } else {
                    while (true) {
                        System.out.println("Your charge is not enough -> If you want to charge enter 'Y' to charge your account. At least you need " + infoAdmin.flights[k].getPrice());
                        string = input.next();
                        if (string.equalsIgnoreCase("Y")) {
                            System.out.println("Enter the amount of money you want to charge");
                            int chargeAmount = input.nextInt();
                            charge(chargeAmount);
                            System.out.println("You added " + passengerUser[i - 1].getCharge() + " to your wallet.");
                            if (passengerUser[i - 1].getCharge() >= infoAdmin.flights[k].getPrice()) {
                                //Set the new amount of charge
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

    /**
     * If the passenger that has bought a ticket, want to cancel it and that flight doesn't have any passenger, admin is allowed to update or delete that flight.
     * @param str By giving an string to the method, it will search whole matrix to check whether that flight is available or not.
     *            If there wasn't that flight, admin will be able to update or delete that flight.
     */
    public void resetAllow(String str) {
        for (int n = 0; n < 15; n++) {
            for (int m = 0; m < 30; m++) {
                if (passengerFlightDetail[n][m] != null && (!passengerFlightDetail[n][m].contains(str))) {
                    infoAdmin.flights[findFlightIndex(str)].setAllow(true);
                }
            }
        }
    }

    /**
     * This method give the FlightID from passenger and find the index of array which all the flights are stored in.
     * @param str By giving an string to the method, it will search for the FlightID which is equal to the string.
     * @return It would return the index of array.
     */
    public int findFlightIndex(String str) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(str)) {
                return k;
            }
        }
        return 100;
    }

    /**
     * Is used to check whether there is enough seat or not.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     * @return If there is enough seat, it would return true, otherwise it would return false.
     */
    public boolean isEnoughSeat(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getSeats() > 0) {
                return true;
            }
        }
        System.out.println("This flight doesn't have empty seat !!!");
        return false;

    }

    /**
     * When passengers buy or cancel a ticket, this method will update the seats.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     */
    public void updateSeat(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                infoAdmin.flights[k].setSeats(infoAdmin.flights[k].getSeats() - 1);
            }
        }
    }

    /**
     * When the user cancel the ticket, the money of that ticket will return to his/her wallet.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     */
    public void resetCharge(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                passengerUser[i - 1].setCharge(passengerUser[i - 1].getCharge() + infoAdmin.flights[k].getPrice());
            }
        }
    }

    /**
     * When the user cancel the ticket, seats of that flight will be updated.
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     */
    public void resetSeat(String string) {
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if (infoAdmin.flights[k] != null && infoAdmin.flights[k].getFlightId().equals(string)) {
                infoAdmin.flights[k].setSeats(infoAdmin.flights[k].getSeats() + 1);
            }
        }

    }

    /**
     * By calling this method, admin wouldn't be able to update or delete intended flight.
     * @param index is the index of flight in array.
     */
    public void isAllowToChange(int index) {
        infoAdmin.flights[index].setAllow(false);
    }

    /**
     * Each user should register to access the features.
     * @param userName is the username of the passenger.
     * @param password is the password of the passenger.
     */
    public void registration(String userName, String password) {
        passengerUser[i] = new User();
        passengerUser[i].setUserName(userName);
        passengerUser[i].setPassword(password);
        passengerFlightDetail[i][15] = new String();
        i++;
        lineOrder = 0;
        System.out.println("Congratulation! Your registration was successful");
    }

    /**
     * Since each passenger have its own unique number -> i, to avoid interference we define i.
     * @param password Every passenger has an unique password, so by giving the password, i can be found easily.
     */
    public void defineI(String password) {
        for (int k = 0; k < passengerUser.length; k++) {
            if (passengerUser[k] != null && passengerUser[k].getPassword().equals(password)) {
                i = k + 1;
            }
        }
    }

    /**
     * A boolean array will be new up to size of flights.
     * As default all the index of array is false.
     * If you want to filter any items of flight, it will make it true.
     */
    public void startFilter() {
        showFilterItems = new Boolean[infoAdmin.flights.length];
        for (int i = 0; i < showFilterItems.length; i++) {
            showFilterItems[i] = false;
        }
    }

    /**
     * Filtering origin of flight.
     * Now the intended section will switch to true.
     */
    public void filterOriginMixed() {
        System.out.println("Filtering Origin (Press N to escape)");
        string = input.next();

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if ((infoAdmin.flights[i] != null) && (infoAdmin.flights[i].getOrigin().equals(string)) && (!string.equalsIgnoreCase("N"))) {
                showFilterItems[i] = true;
            }
        }
    }

    /**
     * Filtering Destination of flight.
     * Now the intended section will switch to true.
     */
    public void filterDestinationMixed() {
        System.out.println("Filtering Destination (Press N to escape)");
        string = input.next();

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if ((infoAdmin.flights[i] != null) && (infoAdmin.flights[i].getDestination().equals(string)) && (!string.equalsIgnoreCase("N"))) {
                showFilterItems[i] = true;
            }
        }
    }

    /**
     * Filtering price range.
     * Now the intended section will switch to true.
     */
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

    /**
     * If any of index changes to true -> shows that passenger decides to filter flights by these features.
     * This method will print only the true values.
     */
    public void printFilters() {
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if (showFilterItems[i].equals(true)) {
                printFlight(i);
            }
        }
    }

    /**
     * To avid confusing for other passengers, the values of boolean array resets each time.
     */
    public void resetFilters() {
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            showFilterItems[i] = true;
        }
    }

    /**
     * This method check whether the price is in the range or not.
     * @param x is the first value -> from...
     * @param y is the second value -> up to...
     * @param i is the index array which flights are stored in.
     * @return If the price is between x and y it returns true otherwise it returns false.
     */
    public boolean checkValueBetween(int x, int y, int i) {
        if (infoAdmin.flights[i].getPrice() >= x && infoAdmin.flights[i].getPrice() <= y) {
            return true;
        }
        return false;
    }

    /**
     * Is used to charge the passenger wallet.
     * @param chargeAmount is the amount of money that would set to passenger's wallet.
     */
    public void charge(int chargeAmount) {
        passengerUser[i - 1].setCharge(chargeAmount);
    }

    /**
     * Passenger can search flights in two ways:
     * First: search by only item like origin, destination etc.
     * Second: search by some item.
     * Note: Passenger can select different items to filter flights.
     */
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

    /**
     * Filtering origin.
     * Passenger can filter only by origin.
     */
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

    /**
     * Filtering destination.
     * Passenger can filter only by destination.
     */
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

    /**
     * Filtering date.
     * Passenger can filter only by date.
     */
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

    /**
     * Filtering time.
     * Passenger can filter only by time.
     */
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

    /**
     * Filtering price.
     * Passenger can filter only by price.
     */
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

    /**
     * If passenger decide filter only by one item, this menu will be printed.
     */
    public void showFilterMenu() {
        System.out.println("    <1> Filter by Origin");
        System.out.println("    <2> Filter by Destination");
        System.out.println("    <3> Filter by Date");
        System.out.println("    <4> Filter by Time");
        System.out.println("    <5> Filter by Price");
    }

    /**
     * Only prints the information of specific flight.
     * @param i is the index of flight in array.
     */
    public void printFlight(int i) {
        System.out.println(".....................................................................................................");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", infoAdmin.flights[i].getFlightId(), infoAdmin.flights[i].getOrigin(), infoAdmin.flights[i].getDestination(), infoAdmin.flights[i].getDate(), infoAdmin.flights[i].getTime(), infoAdmin.flights[i].getPrice(), infoAdmin.flights[i].getSeats());
    }

    /**
     * Show all flights.
     */
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
