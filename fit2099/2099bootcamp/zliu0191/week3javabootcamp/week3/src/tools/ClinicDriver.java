package tools;

import appointment.Appointment;
import clinic.Clinic;

import java.util.Scanner;
import appointment.AppointmentManager;

import static clinic.Clinic.enterToContinue;
import static java.lang.Integer.parseInt;
/**
 * <h1>ClinicDriver</h1>
 * define id address.. of a doctor
 * @author zirui
 * @version 1.0
 * @see
 */
public class ClinicDriver {
    /**
     *
     * @param args arg of main method
     * @throws Exception something may not be handled during running
     */
    public static void main(String[] args) throws Exception {
        Clinic monashClinic = Clinic.getInstance();
        AppointmentManager app=AppointmentManager.getInstance();

//        clinic.runClinic();
        try{

        int selection;
        do {
            selection = MenuInput.menuItem();
            switch (selection) {
                case 1:
                    monashClinic.createPatient();
                    break;
                case 2:
                    monashClinic.createDoctor();
                    break;
                case 3:
                    monashClinic.listPatients();
                    break;

                case 4:
                    monashClinic.listDoctors();
                    break;
                case 5:
                    app.ClinicAppointment( Appointment.AppointmentType.COVID);
                    enterToContinue();
                    break;
                case 6:
                    app.addDateDoses();
                    enterToContinue();
                    break;
                case 7:
                    app.ClinicAppointment( Appointment.AppointmentType.STANDARD);
                    enterToContinue();
                    break;
                case 8:
                    app.addStandardAppointmentCaseDes();
                    break;
                case 9:
                    app.printAppointments();
                    enterToContinue();
                    break;
                case 10:
                    app.cancelDriver();
                    enterToContinue();
                    break;
                case 11:
                    System.exit(0);


            }
        } while (selection != 11);}
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


}
