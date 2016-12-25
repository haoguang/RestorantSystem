/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Job;
import java.sql.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Jonas
 */
public class JobDA {
    private String host = "jdbc:derby://localhost:1527/assignment";
    private String user = "assignment";
    private String password = "assignment";
    private String tableName = "Job";
    private Connection conn;
    private PreparedStatement stmt;
    Statement statement;
    
    public JobDA() {
        createConnection();
    }
    
    public Job getRecord(String jobID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE JobID = ?";
        Job job = null;
        
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, jobID);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                job = new Job(jobID, rs.getString("JobName"), rs.getString("JobDescribtion"), rs.getDouble("BasicSalary"), rs.getBoolean("Authority"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        return job;
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
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public static void main(String[] args) {
        JobDA da = new JobDA();
        Job job = da.getRecord("123A");
        System.out.println(job);
    }
}
