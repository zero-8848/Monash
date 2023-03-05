package appointment;

import appointment.Appointment;
/**
 * <h1>AppointmentManagerInterface</h1>
 * appointment manager interface
 * @author zirui
 * @version 1.0
 * @see
 */
public interface AppointmentManagerInterface {
    void addAppointment(int patientId,int doctorId,String date, Appointment.AppointmentType appType) throws Exception;
    void cancelAppointment(int appointmentId) throws Exception;
    void printAppointments();
    void addDateDoses() throws Exception;
    void addStandardAppointmentCaseDes() throws Exception;
}