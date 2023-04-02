import java.util.Scanner;

public class Welcome {

        public void welcomeMethod(){
            Scanner input = new Scanner(System.in);
            System.out.println("==============================================");
            System.out.println("    Welcome to Airline Reservation System");
            System.out.println("==============================================");
            System.out.println(".................MENU OPTIONS.................");
            System.out.println("    <1> Sign in");
            System.out.println("    <2> Sign up");
            int num = input.nextInt();
            if(num == 1){
                System.out.println("Signing in...");
                User user = new User();
                System.out.println("Enter Your Username");
                String userName = input.next();

                user.setUserName(userName);

                System.out.println("Enter Your Password");
                String password = input.next();
                user.setPassword(password);

                if(user.isAdmin(userName, password)){
                    Admin admin = new Admin();
                    admin.AdminMenu();
                    admin.options();
                    admin.showTable();
                }

            }
            if(num == 2){
                System.out.println("Signing up...");
                System.out.println("Select an Username");
                System.out.println("Select an Password");
            }
        }

}