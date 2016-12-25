/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Job;
import domain.Staff;
import java.sql.*;
import javax.swing.*;
import java.util.*;


/**
 *
 * @author Jonas
 */
public class StaffDA {
    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "assignment";
    private String password = "assignment";
    private String tableName = "Staff";
    private Connection conn;
    private PreparedStatement stmt;
    private JobDA jobDA;
    
    public StaffDA() {
        jobDA= new JobDA();
        createConnection();
    }
    
    public ArrayList<Staff> getRecord(){
        String queryStr = "SELECT * FROM "+tableName + " order by staffID asc";
        ArrayList<Staff> staff =new ArrayList<Staff>();
        Job job = null;
        String date = null;
        try{
            stmt=conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            date = new String(rs.getDate("BOD").toString());
            GregorianCalendar BOD = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8)));
            
            job = jobDA.getRecord(rs.getString("JobID"));
            
            staff.add(new Staff(rs.getString("StaffID"), job,rs.getString("password"),rs.getString("firstname"),rs.getString("lastname")
                    ,rs.getString("address"),rs.getString("IC"),BOD,rs.getString("email"),rs.getString("phoneNo")));
            
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return staff;
    }
    
    public Staff getRecord(String staffID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE staffID = ?";
        Staff staff = null;
        Job job = new Job();
        
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staffID);
            ResultSet rs = stmt.executeQuery();
            
            
            
            if(rs.next()) {
                
                job = jobDA.getRecord(rs.getString("JobID"));
                String date = new String(rs.getDate("BOD").toString());
                GregorianCalendar DOB = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), 
                        Integer.parseInt(date.substring(8)));
                
                staff = new Staff(staffID, job, rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("address"),
                rs.getString("IC"), DOB, rs.getString("email"), rs.getString("phoneNo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return staff;
    }
    
    public void addStaff(Staff staff) {
        String queryStr = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, staff.getStaffID());
            stmt.setObject(2, staff.getJob());
            stmt.setString(3, staff.getPassword());
            stmt.setString(4, staff.getFirstName());
            stmt.setString(5, staff.getLastName());
            stmt.setString(6, staff.getAddress());
            stmt.setString(7, staff.getIC());
            stmt.setDate(8, java.sql.Date.valueOf(String.format("%d-%02d-%02d", staff.getDOB().get(Calendar.YEAR), staff.getDOB().get(Calendar.MONTH),
                    staff.getDOB().get(Calendar.DATE))));
            stmt.setString(9, staff.getEmail());
            stmt.setString(10, staff.getPhoneNo());
            stmt.executeUpdate();
            
        } catch(SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateStaff(Staff staff){
        String queryStr = "UPDATE "+tableName+" set firstname = ?, lastname = ? , address = ? , ic = ?, bod = ?, email = ?, phoneno = ?, registerdate = ?, point = ? where MEMBERID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, staff.getFirstName());
            stmt.setString(2, staff.getLastName());
            stmt.setString(3, staff.getAddress());
            stmt.setString(4, staff.getIC());
            stmt.setDate(5, java.sql.Date.valueOf(String.format("%d-%02d-%02d",staff.getDOB().get(Calendar.YEAR),staff.getDOB().get(Calendar.MONTH),
                    staff.getDOB().get(Calendar.DATE))));
            stmt.setString(6,staff.getEmail());
            stmt.setString(7, staff.getPhoneNo());
            stmt.setString(10, staff.getStaffID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteStaff(Staff staff){
        String queryStr = "delete from "+tableName+" where MEMBERID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);  
            stmt.setString(1,staff.getStaffID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String findID(){
        
        String newID = null;
        ArrayList<Staff> staff = getRecord();
        int lastID = 0;
        
        if(staff.isEmpty()){
            return "S001";
        }
        else{
            int idCheck = Integer.parseInt(staff.get(0).getStaffID().substring(1));
            lastID = Integer.parseInt(staff.get(staff.size()-1).getStaffID().substring(1));
        
        for(int i = 0 ; i < staff.size() ; i++){
            if(idCheck != Integer.parseInt(staff.get(i).getStaffID().substring(1))){
                newID = String.format("S%03d", idCheck);
                break;
            }
            else if(idCheck == lastID){
                newID = String.format("S%03d", lastID+1);
                break;
            }
            idCheck++;
        }
        }
        
        
        return newID;
    }
    
    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void shutDown() {
        if(conn != null)
            try{
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public static void main(String[] args) {
        StaffDA da = new StaffDA();
        da.getRecord();
        System.out.println(da.findID());
    }
}
