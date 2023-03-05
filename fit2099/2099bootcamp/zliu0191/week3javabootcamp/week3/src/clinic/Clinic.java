package clinic;

import appointment.Appointment;
import appointment.AppointmentManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>clinic</h1>
 * general clinic related storage and management
 * @author zirui
 * @version 1.0
 * @see
 */
public class Clinic {
    private static Clinic INSTANCE = null;//instance initialisation
    private ArrayList<Patient> patients;//list of patients
    private ArrayList<Doctor> doctors;//list of doctors


    private Clinic() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    /**get the instance of clinic
     *
     * @return instance
     */
    public static Clinic getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new Clinic();
        }
        return INSTANCE;
    }

    /**
     *
     * @throws Exception throw a exception when invalid input is there
     */
    public void createPatient() throws Exception {
        try{
        try{
        String name, address, caseDesc;
        Scanner sel = new Scanner(System.in);
        System.out.print("Enter clinic.Patient Name:");
        name = sel.nextLine();
        System.out.print("Enter clinic.Patient Address:");
        address = sel.nextLine();
        System.out.print("Enter clinic.Patient Case: ");
        caseDesc = sel.next();
        Patient patient = new Patient(name, address, caseDesc);
        patients.add(patient);}
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }}
        catch(Exception a){
            System.out.println(a.getMessage());
        }
    }

    /**
     * print all pateints
     */
    public void listPatients(){
        String line="----------------------------------------------------------";
        System.out.println(line);
        String colId=String.format("| %-10s ","ID");
        String colName=String.format("| %-10s ","Name");
        String colAddress=String.format("| %-10s ","Address");
        String colCase=String.format("| %-10s |","Case");
        System.out.println(colId+colName+colAddress+colCase);
        System.out.println(line);

        for (int i = 0; i < patients.size(); i++) {
            System.out.println(patients.get(i));
        }
        Scanner con=new Scanner(System.in);
        System.out.println("Enter to continue");
        con.nextLine();
    }

    /**
     *
     * @throws Exception when input invalid
     */

    public void createDoctor() throws Exception {
        try {
            String name, address, speciality;
            Scanner sel = new Scanner(System.in);
            System.out.print("Enter clinic.Doctor Name:");
            name = sel.nextLine();
            System.out.print("Enter clinic.Doctor Address:");
            address = sel.nextLine();
            System.out.print("Enter clinic.Doctor Speciality: ");
            speciality = sel.next();
            Doctor doctor = new Doctor(name, address, speciality);
            doctors.add(doctor);
        }
        catch(Exception e){
            System.out.println("bad input");
        }
    }

    /**
     * print all doctors
     */
    public void listDoctors(){
        try{
        String line="----------------------------------------------------------";
        System.out.println(line);
        String colId=String.format("| %-10s ","ID");
        String colName=String.format("| %-10s ","Name");
        String colAddress=String.format("| %-10s ","Address");
        String colSp=String.format("| %-10s |","Speciality");
        System.out.println(colId+colName+colAddress+colSp);
        System.out.println(line);

        for (int i = 0; i < doctors.size(); i++) {
            System.out.println(doctors.get(i));
        }
        Scanner con=new Scanner(System.in);
        System.out.println("Enter to continue");
        con.nextLine();}
        catch(Exception e){
            System.out.println("bad input");
        }
    }

    /**
     *
     * @throws Exception when clinic cannot run
     */
    public void runClinic() throws Exception {
        System.out.println("Welcome to FIT2099 clinic.Clinic App");
        createPatient();
        listPatients();
        System.out.println("Thank you for visiting FIT2099 clinic.Clinic App");
    }

    /**
     * press enter to continue the program
     */

    public static void enterToContinue(){
        try {
            Scanner con = new Scanner(System.in);
            System.out.println("Enter to continue");
            con.nextLine();
        }
        catch (Exception e){
            System.out.println("bad input");
        }
    }

}

