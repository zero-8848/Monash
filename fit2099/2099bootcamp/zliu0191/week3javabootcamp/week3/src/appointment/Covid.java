
package appointment;

import tools.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>covid</h1>
 * covid method of appointment
 * @author zirui
 * @version 1.0
 * @see
 */
public class Covid extends Appointment
{
    // an arraylist ot store dose dates
    private ArrayList<String> doseDates = new ArrayList<>(4);

    /**
     *
     * @param patientId id of covid patient
     * @param doctorId id of doctor id
     * @param date appointment date
     * @param covid appointment type
     * @throws Exception if any input invalid, throw a expception
     */
    public Covid(int patientId, int doctorId, String date, AppointmentType covid) throws Exception {
        super(patientId, doctorId, date, AppointmentType.COVID);
        boolean b = Utils.validDate(date);
    }

    /**
     *
     * @return format of ID and date
     */

    @Override
    public String toString() {
        String apid=String.format(" %-10s |",super.getAppointmentId());
        String dDate=String.format(" %-10s |","doseDates=" + getDateDose());
        return   apid+dDate;
    }

    /**
     *
     * @param newDate date odf vaccine
     * @throws Exception if date invalid
     */
    @Override
    public void addDateDose(String newDate) throws Exception {
        try{
        boolean b = Utils.validDate(newDate);
        if(doseDates.size()<5 && b){
            doseDates.add(newDate);
        }
        if (!(doseDates.size() <5)){
            throw new IllegalArgumentException("Incorrect CovidDoseDate's details.");
        }
        if (!b){
            throw new IllegalArgumentException("Invalid date");
        }}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        }

    /**
     *
     * @return date of doses
     */
    public ArrayList<String> getDateDose() {
        return doseDates;
    }

}
