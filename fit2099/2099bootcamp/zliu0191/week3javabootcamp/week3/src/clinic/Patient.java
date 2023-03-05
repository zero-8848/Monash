package clinic;

import tools.Utils;
/**
 * <h1>Patient</h1>
 * define id address.. of a Patient
 * @author zirui
 * @version 1.0
 * @see
 */
public class Patient extends Person {
    private String caseDescription;


    public Patient(String fullName, String address, String caseDescription) throws Exception {
        super(fullName, address);
        this.caseDescription = caseDescription;
        setFullName(fullName);
        setAddress(address);
        this.id = generateId();
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    @Override
    public int generateId() {
        return Utils.nextID(100000,9999999);
    }



    @Override
    public String toString() {
        String identity=String.format("| %-10s |",this.id+"");
        String caseDescription=String.format(" %-10s |",getCaseDescription());
        return
                identity+super.toString()+caseDescription;

    }
}
