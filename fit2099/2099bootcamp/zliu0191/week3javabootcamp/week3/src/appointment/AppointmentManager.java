package appointment;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * <h1>AppointmentManager</h1>
 * manage/store appointments
 * @author zirui
 * @version 1.0
 * @see
 */
public class AppointmentManager implements AppointmentManagerInterface {
    private HashMap<Integer, Appointment> ap ;//a hash map to store ap
    private static AppointmentManager appointmentManagerInstance = null;
    //object, the instance of AppointmentManager and initialised the value null

    /**
     * construct appointment
     */
    private AppointmentManager() {
        ap = new HashMap<Integer,Appointment>();
    }

    /**
     * appointment manger
     * @return instance of appointment manager
     */
    public static AppointmentManager getInstance(){
        if (appointmentManagerInstance == null) {
            appointmentManagerInstance = new AppointmentManager();
        }
        return appointmentManagerInstance;
    }

    /**
     * to add appointment to the hash map
     * @param patientId id of patient
     * @param doctorId id of doctor
     * @param date  date of appointment
     * @param appType type of appointment
     */
    // Maybe danger as appointment is an abstract
    @Override
    public void addAppointment(int patientId, int doctorId, String date, Appointment.AppointmentType appType) {
    try {
        if (appType == Appointment.AppointmentType.COVID) {
            Appointment apadd = new Covid(patientId, doctorId, date, Appointment.AppointmentType.COVID);
            ap.put(apadd.getAppointmentId(), apadd);
        }
        if (appType == Appointment.AppointmentType.STANDARD) {
            Appointment apadd = new Standard(patientId, doctorId, date, Appointment.AppointmentType.STANDARD);
            ap.put(apadd.getAppointmentId(), apadd);
        }
    }
    catch (Exception e){
        System.out.println(e.getMessage());
    }

    }

    /**
     * cancle the appoinment of chosen ID
     * @param appointmentId id of appointment to cancel
     */

    @Override
    public void cancelAppointment(int appointmentId) {
        ap.remove(appointmentId);
    }

    /**
     * print out detail of the appointment
     */
    @Override
    public void printAppointments() {
        String line="----------------------------------------------------------";
        System.out.println(line);
        String colId=String.format("| %-10s ","ID");
        String app=String.format("| %-10s ","appointment");
        System.out.println(colId+app);
        System.out.println(line);

        for (Appointment appointment:ap.values()) {
            System.out.println(appointment.toString());
        }
//        Scanner con=new Scanner(System.in);
//        System.out.println("Enter to continue");
//        con.nextLine();
    }

    /**
     * add dose dates to the appointment
     * @throws Exception throw if dose date is invalid
     */

    @Override
    public void addDateDoses() throws Exception {
        try {
            String doseDate;
            Scanner sel = new Scanner(System.in);
            System.out.print("Enter Appointment ID:");
            int AppointmentId = Integer.parseInt(sel.nextLine());
            System.out.print("Enter Dose Date:");
            doseDate = sel.next();

            try {
                for (Appointment currentAppointment : ap.values()) {
                    if (currentAppointment.getAppointmentId() == AppointmentId) {
                        currentAppointment.addDateDose(doseDate);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        catch (Exception n){
            System.out.println(n.getMessage());
        }
    }

    /**
     * add case description to a standard case
     * @throws IllegalArgumentException if date is invalid throw
     */

    @Override
    public void addStandardAppointmentCaseDes() throws IllegalArgumentException {
        String appointmentCase;
        try{
        Scanner sel = new Scanner(System.in);
        System.out.print("Enter Appointment ID:");
        int AppointmentId = Integer.parseInt(sel.nextLine());
        System.out.print("Enter Appointment Case Description:");
        appointmentCase = sel.nextLine();
        try{        for (Appointment currentAppointment : ap.values()) {
            if (currentAppointment.getAppointmentId() == AppointmentId) {
                currentAppointment.addStandardCaseDes(appointmentCase);
            }
        }
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("wrong");
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("wrong");
        }
    }

    /**
     * add one clinic appointment
     * @param appointmentType type of clinic appointment
     */
    public void ClinicAppointment( Appointment.AppointmentType appointmentType){
        Scanner sel = new Scanner(System.in);
        String date;
        int patientId,doctorId;
        //                    Scanner sel = new Scanner(System.in);
        try{
        System.out.print("Enter patient id");
        patientId = Integer.parseInt(sel.nextLine());
        System.out.print("Enter clinic.Doctor id:");
        doctorId = Integer.parseInt(sel.nextLine());
        System.out.print("Enter appointment date: ");
        date = sel.next();
        addAppointment(patientId,doctorId,date,appointmentType);
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }



    }

    /**
     * cancel of an appointment
     */
    public void cancelDriver(){
        try {
            int appoinmentId;
            Scanner sel = new Scanner(System.in);
            System.out.print("Enter appointment id");
            appoinmentId = sel.nextInt();

            cancelAppointment(appoinmentId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
