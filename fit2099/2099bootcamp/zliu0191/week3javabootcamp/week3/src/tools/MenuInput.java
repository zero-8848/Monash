package tools;

import java.util.Scanner;
/**
 * <h1>menuinput </h1>
 * input menu list
 * @author zirui
 * @version 1.0
 * @see
 */
public class MenuInput {
    /**
     *
     * @return reuturn the choice of user 
     */
    public static int menuItem() {
        int choice=0;
        Scanner sel = new Scanner(System.in);
        System.out.println("+-------------------------------------+");
        System.out.println("|        Welcome to Bootcamp          |");
        System.out.println("|        Week 4  (Clinic App)         |");
        System.out.println("+-------------------------------------+");
        System.out.println("1) New Patient");
        System.out.println("2) New Doctor");
        System.out.println("3) List Patients");
        System.out.println("4) List Doctors");
        System.out.println("5) Schedule COVID Appointment");
        System.out.println("6) Add COVID Dose");
        System.out.println("7) Schedule Standard Appointment");
        System.out.println("8) Add Standard Appointment Case");
        System.out.println("9) List Appointment");
        System.out.println("10) Cancel Appointment");
        System.out.println("11) Exit");
        System.out.print("Select one:");
        choice = Integer.parseInt(sel.nextLine());
        System.out.println("Your choice:"+choice);
        return choice;
    }

}
