import java.util.Scanner;

public class Admin {

    // Since there are 3 flights as default, the index(i) is 2. So now i starts from 2 and it would increase gradually in each step(i++).
    int i = 2;
    Scanner input = new Scanner(System.in);
    String string = "";
    String str = "";

    int num;

    /**
     * Since we just need to execute table of flights once, this function is called in constructor.
     */
    public Admin() {
        tableOfFlights();
    }

    /**
     * Show the Menu of Admin.
     */
    public void showAdminMenu() {
        System.out.println("===========================");
        System.out.println("    Admin MENU OPTIONS");
        System.out.println("===========================");
        System.out.println(". . . . . . . . . . . . . .");
        System.out.println("    <1> Add");
        System.out.println("    <2> Update");
        System.out.println("    <3> Remove");
        System.out.println("    <4> Flight schedules");
        System.out.println("    <0> Sign out");
    }

    /**
     * Just to show the admin menu after each action.
     */
    public void showAdminMenuAgain(){
        System.out.println("Press any key to continue...");
        string = input.next();
        System.out.print("\033[H\033[2J");
        showAdminMenu();
    }

    /**
     * When the menu showed, by entering any button which is designed here, the program will start to run.
     */
    public void options() {
        int number = 10;
        while (number != 0) {
            number = input.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Adding...");
                    add();
                    showAdminMenuAgain();
                    break;
                case 2:
                    System.out.println("Updating...");
                    System.out.println("Enter the flight ID:");
                    string = input.next();
                    update(string);
                    showAdminMenuAgain();
                    break;
                case 3:
                    System.out.println("Deleting...");
                    System.out.println("Enter the flight ID:");
                    string = input.next();
                    delete(string);
                    showAdminMenuAgain();
                    break;
                case 4:
                    showTable();
                    showAdminMenuAgain();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }

    // Array of flights. In this airline you can have only 15 flights.
    public FlightInfo[] flights = new FlightInfo[15];

    /**
     * In this method our default flights are initialized.
     */
    public void tableOfFlights() {

        flights[0] = new FlightInfo("WX-12", "Yazd", "Tehran", "1401-12-10", "12:30", 700_000, 51);
        flights[1] = new FlightInfo("WZ-15", "Mashhad", "Ahvaz", "1401-12-11", "08:00", 900_000, 245);
        flights[2] = new FlightInfo("BG-22", "Shiraz", "Tabriz", "1401-12-12", "22:30", 1_100_000, 12);
    }

    /**
     * The details of all fights will be printed by calling this method.
     */
    public void showTable() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null) {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());
                System.out.println(".....................................................................................................");
            }
        }
    }

    /**
     * Admin is able to add a new flight.
     * Hint: Admin is allowed to add flights up to 15.
     */
    public void add() {
        i++;
        flights[i] = new FlightInfo();
        System.out.println("Enter FlightID:");
        flights[i].setFlightId(input.next());

        System.out.println("Enter Origin:");
        flights[i].setOrigin(input.next());

        System.out.println("Enter Destination:");
        flights[i].setDestination(input.next());

        System.out.println("Enter Date:");
        flights[i].setDate(input.next());

        System.out.println("Enter Time:");
        flights[i].setTime(input.next());

        System.out.println("Enter Price:");
        flights[i].setPrice(input.nextInt());

        System.out.println("Enter Free Seats:");
        flights[i].setSeats(input.nextInt());
    }

    /**
     * Admin is able to delete the intended flight.
     *
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     *               Then, the value of that string will be null, so that it can't be recognized by search engines.
     */
    public void delete(String string) {
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null && flights[i].getFlightId().equals(string)) {
                if (!flights[i].isAllow()) {
                    System.out.println("Some people has registered this flight.\nYou can't delete this flight!");
                    break;
                }
                flights[i] = null;
                System.out.println("Flight deleted successfully");
            } else {
                System.out.println("This flight ID is not valid");
                break;
            }
        }
    }

    /**
     * Admin is able to update any feature of flights except FlightID which is unique.
     *
     * @param string By giving an string to the method, it will search for the FlightID which is equal to the string.
     *               So that you have accesses to any details of intended flight.
     */
    public void update(String string) {
        int flag = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null && flights[i].getFlightId().equals(string)) {
                flag = 1;
                if (!flights[i].isAllow()) {
                    System.out.println("Some people has registered this flight.\nYou can't update this flight!");
                    break;
                }
                System.out.println("Update " + flights[i].getFlightId());
                updateOrigin(i);

                updateDestination(i);

                updateDate(i);

                updateTime(i);

                updatePrice(i);

                updateSeats(i);
                System.out.println("Flight updated successfully");
            }
        }
        if (flag == 0) {
            System.out.println("This flight ID is not valid");
        }
    }

    /**
     * Update the seats of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updateSeats(int i) {
        System.out.println("Change Seats (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setSeats(Integer.parseInt(str));
        }
    }

    /**
     * Update the price of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updatePrice(int i) {
        System.out.println("Change Price (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setPrice(Integer.parseInt(str));
        }
    }

    /**
     * Update the time of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updateTime(int i) {
        System.out.println("Change Time (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setTime(str);
        }
    }

    /**
     * Update the date of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updateDate(int i) {
        System.out.println("Change Date (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setDate(str);
        }
    }

    /**
     * Update the destination of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updateDestination(int i) {
        System.out.println("Change Destination (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setDestination(str);
        }
    }

    /**
     * Update the origin of each flight.
     *
     * @param i is used to define the index of intended flight in array(FlightInfo).
     */
    private void updateOrigin(int i) {
        System.out.println("Change Origin (Press N to escape)");
        str = input.next();
        if (!str.equalsIgnoreCase("N")) {
            flights[i].setOrigin(str);
        }
    }
}
