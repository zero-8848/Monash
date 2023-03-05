package appointment;
import tools.Utils;

/**
 * <h1>Appointments</h1>
 * abstract appointment method of
 * @author zirui
 * @version 1.0
 * @see
 */
public abstract class Appointment {
private int appointmentId;// id of appoint ment
private int patientId;//id of patient(unique)
private int doctorId;//Id of doctor
private String date;//which day the appointment happened

//    public abstract void addDateDoses() throws Exception;

    /**
     * add dose date
     * @param doseDate date odf vaccine
     * @throws Exception date may go invalid
     */
    public void addDateDose(String doseDate) throws Exception {
    }

    /**
     * add case discription
     * @param appointmentCase which case to describe
     */
    public void addStandardCaseDes(String appointmentCase) {
    }

    /**
     * enmu store Appointment Types
     */
    public enum AppointmentType{
    COVID, STANDARD
}

    /**
     *
     * @param patientId id of patient
     * @param doctorId id of doctor
     * @param date appoint date
     * @param covid appoint type
     */
    public Appointment(int patientId, int doctorId, String date, AppointmentType covid) {
        this.appointmentId = Utils.nextID(1000000,9999999);
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    /**
     *
     * @return id of appointment
     */
    public int getAppointmentId() {
        return appointmentId;
    }



}
