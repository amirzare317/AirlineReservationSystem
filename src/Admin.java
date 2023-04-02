import javax.management.MBeanAttributeInfo;
import java.util.Scanner;

public class Admin {
    public void AdminMenu(){
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
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        switch (number){
            case 1:
                //--------------
                break;
            case 2:
                //-------------
                break;
            case 3:
                //-----------------
                break;
            case 4:
                tableOfFlights();
                break;
            case 0:
                //------------------
                break;
            default:
                System.out.println("Wrong input!!!");
                break;

        }
    }
    FlightInfo[] flights = new FlightInfo[5];
    public void tableOfFlights(){

        flights[0] = new FlightInfo("WX-12" , "Yazd", "Tehran", "1401-12-10", "12:30", 700_000, 51 );
        flights[1] = new FlightInfo("WZ-15", "Mashhad", "Ahvaz", "1401-12-11", "08:00", 900_000, 245);
        flights[2] = new FlightInfo("BG-22", "Shiraz", "Tabriz", "1401-12-12", "22:30", 1_100_000, 12);
    }
    public void showTable(){
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        for (int i = 0; i < 3; i++) {
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15d %-15d\n", flights[i].getFlightId(), flights[i].getOrigin(), flights[i].getDestination(), flights[i].getDate(), flights[i].getTime(), flights[i].getPrice(), flights[i].getSeats());
            System.out.println(".....................................................................................................");

        }
    }
}
