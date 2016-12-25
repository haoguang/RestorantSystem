
package domain;

import java.util.GregorianCalendar;

public class Staff extends Person{
    private String staffID;
    private Job job;
    private String password;

    public Staff() {
        
    }

    public Staff(String staffID, Job job, String password,String firstName, String lastName, String address, String IC, GregorianCalendar DOB, String email, String phoneNo) {
        super(firstName,lastName,address, IC, DOB, email, phoneNo);
        this.staffID = staffID;
        this.job = job;
        this.password = new String(password);
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean changePassword(String oldPassword, String newPassword){
        if(password.equals(oldPassword)){
            password = new String(newPassword);
            return true;
        }
         return false;   
    }

    @Override
    public String toString() {
        return super.toString() + "Staff{" + "staffID=" + staffID + ", job=" + job + '}';
    }
    
    


    
    
}
