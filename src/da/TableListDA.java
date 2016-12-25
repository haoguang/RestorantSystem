/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import da.TableListDA;
import domain.Tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author khooe
 */
public class TableListDA {

        private String host = "jdbc:derby://localhost:1527/assignment";
        private String tableName = "Tables";
        private String user = "assignment";
        private String password = "assignment";
        private Connection conn;
        private PreparedStatement stmt;
    private Object tables;
        
    public TableListDA(){
        createConnection();
    }
    
    public Tables getRecord(int tableNo){
        String queryStr = "SELECT * FROM "+tableName +" WHERE TableNo = ?";
        Tables tables = null;
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setInt(1, tableNo);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                tables = new Tables(rs.getInt("tableno"),rs.getInt("seat"),rs.getString("status"),rs.getString("holdingOrder"));

            }
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
       
    
        return tables;
    }
    
    public ArrayList<Tables> getRecord(){
        String queryStr = "SELECT * FROM "+tableName +" order by TABLENO asc";
        ArrayList<Tables> tables = new ArrayList<Tables>();
        
        try{
            stmt=conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Tables tablesTemp = new Tables(rs.getInt("tableno"),rs.getInt("seat"),rs.getString("status"),rs.getString("holdingOrder"));
                tables.add(tablesTemp);
            }
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
       
    
        return tables;
    }  
    
    public void addTable(Tables tables){
        String queryStr = "INSERT INTO "+tableName +" (tableno, seat, status) VALUES (?,?,?)";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setInt(1, tables.getTableNo());
            stmt.setInt(2, tables.getSeat());
            stmt.setString(3, tables.getStatus());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateTable(Tables tables){
        String queryStr = "UPDATE Tables set seat = ?, status = ? where tableno = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setInt(1, tables.getSeat());
            stmt.setString(2, tables.getStatus());
            stmt.setInt(3, tables.getTableNo());

            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteTable(Tables tables){
        String queryStr = "delete from tables where tableno = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);  
            stmt.setInt(1,tables.getTableNo());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateStatus(Tables tables){
        String queryStr = "UPDATE Tables set status = ?, holdingorder = ? where tableno = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, tables.getStatus());
            stmt.setString(2, tables.getHoldingOrder());
            stmt.setInt(3, tables.getTableNo());

            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        TableListDA da = new TableListDA();
        System.out.println(da.findID());
    }
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("Connected");
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int findID(){
        
        int newID = 0;
        ArrayList<Tables> tables = getRecord();
        int lastID = 0;
        
        if(tables.isEmpty()){
            return 1;
        }
        else{
            int idCheck = tables.get(0).getTableNo();
            lastID = tables.get(tables.size()-1).getTableNo();
        
        for(int i = 0 ; i < tables.size() ; i++){
            if(idCheck != tables.get(i).getTableNo()){
                newID = idCheck;
                break;
            }
            else if(idCheck == lastID){
                newID = lastID+1;
                break;
            }
            idCheck++;
        }
        }
        
        
        return newID;
    }

    
}
