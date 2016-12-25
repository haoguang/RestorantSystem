/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.StaffDA;
import da.StaffDA;
import domain.Staff;

/**
 *
 * @author Jonas
 */
public class StaffControl {
    
    private StaffDA staffDA; 
    
    public StaffControl() {
        staffDA = new StaffDA();
    }
    
    public Staff selectRecord(String staffID) {
        return staffDA.getRecord(staffID);
    }
    
    public void addRecord(Staff staff) {
        staffDA.addStaff(staff);
    }
    
    public void updateRecord(Staff staff) {
        staffDA.updateStaff(staff);
    }
    
    public void deleteRecord(Staff staff) {
        staffDA.deleteStaff(staff);
    }
    
    public String findNewID() {
        return staffDA.findID();
    }
}
