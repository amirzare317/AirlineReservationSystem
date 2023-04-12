import java.util.Scanner;

public class Admin {

    int i = 2;
    Scanner input = new Scanner(System.in);
    String string = new String();
    String str = new String();

    int num;

    public Admin(){
        tableOfFlights();
    }

    public void showAdminMenu(){
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

    public void options(){
        int number = 10;

        while (number != 0) {
            showAdminMenu();
            number = input.nextInt();
            switch (number) {
                case 1:
                    System.out.println("Adding...");
                    add();
//                    showMessageOfAdminMenu();
                    break;
                case 2:
                    System.out.println("Updating...");
                    System.out.println("Enter the flight ID:");
                    string = input.next();
                    update(string);
//                    showMessageOfAdminMenu();
                    break;
                case 3:
                    System.out.println("Deleting...");
                    System.out.println("Enter the flight ID:");
                    string = input.next();
                    delete(string);

//                    showMessageOfAdminMenu();
                    break;
                case 4:
                    showTable();
//                    showMessageOfAdminMenu();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }
    public FlightInfo[] flights = new FlightInfo[15];
    public void tableOfFlights(){

        flights[0] = new FlightInfo("WX-12" , "Yazd", "Tehran", "1401-12-10", "12:30", 700_000, 51 );
        flights[1] = new FlightInfo("WZ-15", "Mashhad", "Ahvaz", "1401-12-11", "08:00", 900_000, 245);
        flights[2] = new FlightInfo("BG-22", "Shiraz", "Tabriz", "1401-12-12", "22:30", 1_100_000, 12);
    }
    public void showTable(){
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < flights.length; i++) {
            if(flights[i] != null){
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());
            System.out.println(".....................................................................................................");
        }
        }
    }
    public void add(){
        ++i;
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
    public void delete(String string){
        for (int i = 0; i < flights.length; i++) {
            if (flights[i] != null && flights[i].getFlightId().equals(string)){
                flights[i] = null;
                System.out.println("Flight deleted successfully");
            }
            else {
                System.out.println("This flight ID is not valid");
                break;
            }
        }
    }

    public void update(String string){
        for (int i = 0; i < flights.length; i++) {
            if(flights[i] != null && flights[i].getFlightId().equals(string)){
                System.out.println("Update " + flights[i].getFlightId());
                updateOrigin(i);

                updateDestination(i);

                updateDate(i);

                updateTime(i);

                updatePrice(i);

                updateSeats(i);
                System.out.println("Flight updated successfully");
            }
            else {
                System.out.println("This flight ID is not valid");
                break;
            }
        }
    }

    private void updateSeats(int i) {
        System.out.println("Change Seats (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setSeats(Integer.parseInt(str));
        }
    }

    private void updatePrice(int i) {
        System.out.println("Change Price (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setPrice(Integer.parseInt(str));
        }
    }

    private void updateTime(int i) {
        System.out.println("Change Time (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setTime(str);
        }
    }

    private void updateDate(int i) {
        System.out.println("Change Date (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setDate(str);
        }
    }

    private void updateDestination(int i) {
        System.out.println("Change Destination (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setDestination(str);
        }
    }

    private void updateOrigin(int i) {
        System.out.println("Change Origin (Press N to escape)");
        str = input.next();
        if(!str.equalsIgnoreCase("N")){
            flights[i].setOrigin(str);
        }
    }



}
