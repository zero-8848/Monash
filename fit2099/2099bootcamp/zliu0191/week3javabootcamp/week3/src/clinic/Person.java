package clinic;
/**
 * <h1>Person</h1>
 * abstract about person(doctors and patients
 * @author zirui
 * @version 1.0
 * @see
 */
public abstract class Person {
    /**
     * fullName
     */
    private String fullName;//full name of a person
    private String address;// home add of person
    protected int id;// person id


    /**
     *
     * @param aFullName initialise person full name
     * @param anAddress initialse add
     * @throws Exception when a person's detail goes wrong(see req)
     */
    public Person(String aFullName, String anAddress) throws  Exception {
        if (setFullName(aFullName) && setAddress(anAddress)) {
            setId(generateId());
        }else{
            throw new Exception("Incorrect Person's details ");
        }
    }

    /**
     * set person id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return get full name of person
     */

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

//    public void setFullName(String fullName) {
//
//
//        this.fullName = fullName;
//    }

    /**set full name and check name valiadation
     *
     * @param aFullName person full name ,string
     * @return if name valid (bool)
     */
    public boolean setFullName(String aFullName) {
    boolean isValid=false;
    if(aFullName.length()>=3 && aFullName.length()<=15){
        isValid=true;
        fullName = aFullName;
    }
    return (isValid);
}

    /**
     * set address and validation
     * @param aAddress
     * @return
     */
    public boolean setAddress(String aAddress) {
        boolean isValid=false;
        if(aAddress.length()>=3 && aAddress.length()<=15){
            isValid=true;
            address = aAddress;
        }
        return (isValid);
    }

    /**
     *
     * @return format of name and addr
     */
    @Override
    public String toString() {
        String name=String.format(" %-10s |",getFullName());
        String address=String.format(" %-10s |",getAddress());
        return   name +address;
    }

    abstract public int generateId();

}
