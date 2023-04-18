import java.util.Scanner;

public class Welcome {
    public void welcomeMethod() {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();
        Passenger passenger = new Passenger();
        passenger.infoAdmin = admin;
        User user = new User();
        user.userInfo = passenger;

        while (true) {
            System.out.println("==============================================");
            System.out.println("    Welcome to Airline Reservation System");
            System.out.println("==============================================");
            System.out.println(".................MENU OPTIONS.................");
            System.out.println("    <1> Sign in");
            System.out.println("    <2> Sign up");
            int num = input.nextInt();
            if (num == 1) {
                System.out.println("Signing in...");

                System.out.println("Enter Your Username");
                String userName = input.next();
                user.setUserName(userName);

                System.out.println("Enter Your Password");
                String password = input.next();
                user.setPassword(password);

                if (user.isAdmin(userName, password)) {
                    admin.options();
                }
                if (user.isRegisteredBefore(userName, password)) {
                    passenger.showPassengerMenu();
                    passenger.passengerOption();
                }
                else {
                    System.out.println("Incorrect input !!!");
                }

            }
            if (num == 2) {
                System.out.println("Signing up...");

                System.out.println("Create an Username");
                String passengerUserName = input.next();

                System.out.println("Create an Password");
                String passengerPassword = input.next();

                passenger.registration(passengerUserName, passengerPassword);

                passenger.showPassengerMenu();
                passenger.passengerOption();

            }
        }
    }

}
