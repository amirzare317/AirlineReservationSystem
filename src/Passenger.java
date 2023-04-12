import java.util.Scanner;

public class Passenger {
    Scanner input = new Scanner(System.in);
    Admin admin = new Admin();
    User[] passengerUser = new User[30];
    String string = new String();
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
                    System.out.println("Changing password...");
                    System.out.println("Enter your new password: ");
                    string = input.next();
                    passengerUser[i].setUserName(string);
                    break;
                case 2:
                    System.out.println("Searching flight tickets...");
                    admin.search();
                    break;
                case 3:
                    System.out.println("Booking tickets");
                    break;
                case 4:
                    System.out.println(":)))))");
                    break;
                case 5:
                    System.out.println(":)))))");
                    break;
                case 6:
                    System.out.println("Adding charge...");
                    System.out.println("Enter the amount of money you want to charge");
                    int chargeAmount = input.nextInt();
                    charge(chargeAmount);
                    System.out.println("Your charge is: " + passengerUser[i].getCharge());
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
        passengerUser[i] = new User();
        passengerUser[i].setUserName(userName);
        passengerUser[i].setPassword(password);
    }
    public void charge(int chargeAmount){
        passengerUser[i].setCharge(chargeAmount);
    }


}
