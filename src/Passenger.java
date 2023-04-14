import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);
    Admin infoAdmin = new Admin();
    User[] passengerUser = new User[30];
    String string = new String();

    int i = 0;
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
                    passengerUser[i].setUserName(string);
                    break;
                case 2:
                    System.out.println("Searching flight tickets...");
                    search();
                    break;
                case 3:
                    System.out.println("Booking tickets");
                    System.out.println("Would you like to see flight table once again? Y or N");
                    string = input.next();
                    bookTickets(string);
                    break;
                case 4:
                    System.out.println("Cancelling...");
                    break;
                case 5:
                    System.out.println("Booked tickets...");
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

    private void bookTickets(String string) {
        if(string.equalsIgnoreCase("Y")){
            showAllFlights();
        }
        System.out.println("Enter your intended flight ID to book it.");
        string = input.next();
        int flag = 0;
        for (int k = 0; k < infoAdmin.flights.length; k++) {
            if(infoAdmin.flights[k].getFlightId().equals(string)){
                isAllowToChange(k);
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("This flight doesn't exist");
        }
    }
    public void isAllowToChange(int number){
        infoAdmin.flights[number].setAllow(false);
    }

    public void registration(String userName , String password){
        passengerUser[i] = new User();
        passengerUser[i].setUserName(userName);
        passengerUser[i].setPassword(password);
        i++;
    }
    public void charge(int chargeAmount){
        passengerUser[i].setCharge(chargeAmount);
    }


    public void search(){
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
                System.out.println("This feature is in progress :)");

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
        if(flag == 0){
            System.out.println("Your intended flight is not available");
        }
    }
    public void filterDestination() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(infoAdmin.flights[i] != null && infoAdmin.flights[i].getDestination().equals(string)){
                printFlight(i);
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("Your intended flight is not available");
        }
    }
    public void filterDate() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(infoAdmin.flights[i] != null && infoAdmin.flights[i].getDate().equals(string)){
                printFlight(i);
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("Your intended flight is not available");
        }
    }
    public void filterTime() {
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(infoAdmin.flights[i] != null && infoAdmin.flights[i].getTime().equals(string)){
                printFlight(i);
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("Your intended flight is not available");
        }
    }

    public void filterPrice() {
        string = input.next();
        int flag = 0;
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(infoAdmin.flights[i] != null && infoAdmin.flights[i].getPrice() == Integer.parseInt(string)){
                printFlight(i);
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("Your intended flight is not available");
        }
    }
    public void showFilterMenu(){
        System.out.println("    <1> Filter by Origin");
        System.out.println("    <2> Filter by Destination");
        System.out.println("    <3> Filter by Date");
        System.out.println("    <4> Filter by Time");
        System.out.println("    <5> Filter by Price");
    }
    private void printFlight(int i) {
        System.out.println(".....................................................................................................");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", infoAdmin.flights[i].getFlightId(), infoAdmin.flights[i].getOrigin(), infoAdmin.flights[i].getDestination(), infoAdmin.flights[i].getDate(), infoAdmin.flights[i].getTime(), infoAdmin.flights[i].getPrice(), infoAdmin.flights[i].getSeats());
    }
    public void showAllFlights(){
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(infoAdmin.flights[i] != null){
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", infoAdmin.flights[i].getFlightId(), infoAdmin.flights[i].getOrigin(), infoAdmin.flights[i].getDestination(), infoAdmin.flights[i].getDate(), infoAdmin.flights[i].getTime(), infoAdmin.flights[i].getPrice(), infoAdmin.flights[i].getSeats());
                System.out.println(".....................................................................................................");
            }
        }
    }


















    /*public List<FlightInfo> filterFlights(FlightInfo[] flights, String origin, String destination, String date, String time) {
        List<FlightInfo> filteredFlights = new ArrayList<>();
        for (FlightInfo flight : flights) {
            if (flight.getOrigin().equals(origin) &&
                    flight.getDestination().equals(destination) &&
                    flight.getDate().equals(date) &&
                    flight.getTime().equals(time)
            ) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
    */

/*
    boolean flagOrigin = true;
    boolean flagDestination = true;
    boolean flagDate = true;
    boolean flagTime = true;
    boolean flagPrice = true;
    String wantedOrigin;
    String wantedDestination;
    String wantedDate;
    String wantedTime;
    int wantedPrice;
    public void showFilter(){
        for (int i = 0; i < infoAdmin.flights.length; i++) {
            if(flagOrigin && infoAdmin.flights[i].getOrigin() != null){
                System.out.println(infoAdmin.flights[i].getFlightId());
            }
            if(flagDestination && infoAdmin.flights[i].getDestination() != null){
                System.out.println(infoAdmin.flights[i].getFlightId());
            }
            if(flagDate && infoAdmin.flights[i].getDate() != null){
                System.out.println(infoAdmin.flights[i].getFlightId());
            }
            if(flagTime && infoAdmin.flights[i].getTime() != null){
                System.out.println(infoAdmin.flights[i].getFlightId());
            }

        }
    }
    */

}
