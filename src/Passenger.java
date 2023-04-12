import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);

    User[] passengerUser = new User[30];
    int i = 0;


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
        System.out.println("    <0> Sign out");
    }

    public void passengerOption() {
        int number = 10;

        while (number != 0) {
            number = input.nextInt();
            switch (number) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }

    public void registration(String userName , String password){
        i++;
        passengerUser[i].setUserName(userName);
        passengerUser[i].setPassword(password);
    }

}
