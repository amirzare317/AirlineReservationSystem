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
                //-----------------
                break;
            case 0:
                //------------------
                break;
            default:
                System.out.println("Wrong input!!!");
                break;

        }
    }
}
