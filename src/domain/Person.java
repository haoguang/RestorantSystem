package domain;

import java.util.GregorianCalendar;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String IC;
    private GregorianCalendar DOB;
    private String email;
    private String phoneNo;

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String IC, GregorianCalendar DOB, String email, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.IC = IC;
        this.DOB = DOB;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIC() {
        return IC;
    }

    public void setIC(String IC) {
        this.IC = IC;
    }

    public GregorianCalendar getDOB() {
        return DOB;
    }

    public void setDOB(GregorianCalendar DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", IC=" + IC + ", DOB=" + DOB + ", email=" + email + ", phoneNo=" + phoneNo + '}';
    }

    

    
    
    
}
