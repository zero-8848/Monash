package appointment;

import tools.Utils;
/**
 * <h1>Standard</h1>
 * contain tools that can be used globally
 * @author zirui
 * @version 1.0
 * @see
 */
public class Standard extends Appointment{
private String caseDescription;



    public Standard(int patientId, int doctorId, String date, AppointmentType covid) throws Exception {
        super(patientId, doctorId, date, AppointmentType.STANDARD);
        boolean b = Utils.validDate(date);
//        setCaseDescription(caseDescription);
    }

    /**
     *
     * @return string format id and case
     */
    @Override
    public String toString() {
        String apid=String.format(" %-10s |",super.getAppointmentId());
        String ca=String.format(" %-10s |","Case description=" +caseDescription);
        return   apid+ca;
    }

    /**validation check of case description
     *
     * @param aCaseDescription case des
     * @return If the description is valid
     */
    public boolean setCaseDescription(String aCaseDescription) {
        boolean isValid=false;
        if(aCaseDescription.length()>=5 && aCaseDescription.length()<=15){
            isValid=true;
            this.caseDescription = aCaseDescription;
        }
        return (isValid);
    }

    /**add standard cases description of the form
     *
     * @param appointmentCase which case to describe
     */
    @Override
    public void addStandardCaseDes(String appointmentCase) {
        super.addStandardCaseDes(appointmentCase);
        setCaseDescription(appointmentCase);
    }
}
