package clinic;

import tools.Utils;
/**
 * <h1>Doctor</h1>
 * define id address.. of a doctor
 * @author zirui
 * @version 1.0
 * @see
 */
public class Doctor extends Person {
    private String speciality;// speciality of a doctor

    public Doctor(String fullName, String address,String speciality) throws Exception {
        super(fullName, address);
        setSpeciality(speciality);
        setFullName(fullName);
        setAddress(address);
        this.id = generateId();
        if(!setSpeciality(speciality)){
            throw new IllegalArgumentException("Incorrect Doctor's speciality");
        }
    }


    @Override
    public int generateId() {
        return Utils.nextID(100,999);
    }

    public String getSpeciality() {
        return speciality;
    }

//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }
    public boolean setSpeciality(String aspeciality) {
        boolean isValid=false;
        if(aspeciality.length()>=2 && aspeciality.length()<=10){
            isValid=true;
            speciality = aspeciality;
        }
        return (isValid);
    }

    @Override
    public String toString() {
        String identity=String.format("| %-10s |",this.id+"");
        String spec=String.format(" %-10s |",getSpeciality());
        return
                identity+super.toString()+spec;

    }

}
