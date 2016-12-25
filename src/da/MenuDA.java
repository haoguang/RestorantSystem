/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;
import da.MenuDA;
import domain.Menu;

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
public class MenuDA {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String tableName = "Menu";
    private String user = "assignment";
    private String password = "assignment";
    private Connection conn;
    private PreparedStatement stmt;
    
    public MenuDA(){
        createConnection();
    }
    
    public ArrayList<Menu> getRecord(){
        String queryStr = "SELECT * FROM "+tableName + " ORDER BY FOODID asc";
        ArrayList<Menu> menu = new ArrayList<Menu>();
        
        try{
            stmt=conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Menu menuTemp = new Menu(rs.getString("foodid"),rs.getString("name"),rs.getString("description"),rs.getDouble("price"),rs.getString("categorise").charAt(0));
                menu.add(menuTemp);
            }
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return menu;
    }
    
    public void addRecord(Menu menu){
        String queryStr = "INSERT INTO "+tableName +" (foodid,name,description,price,categorise) VALUES (?,?,?,?,?)";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, menu.getFoodID());
            stmt.setString(2, menu.getName());
            stmt.setString(3, menu.getDescription());
            stmt.setDouble(4, menu.getPrice());
            stmt.setString(5, ""+menu.getCategorise());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateItem(Menu menu){
        String queryStr = "UPDATE Menu set name = ?,description = ?, price = ?,categorise = ? where foodId = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, menu.getName());
            stmt.setString(2, menu.getDescription());
            stmt.setDouble(3, menu.getPrice());
            stmt.setString(4, ""+menu.getCategorise());
            stmt.setString(5, menu.getFoodID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteItem(Menu menu){
        String queryStr = "delete from menu where foodid = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);  
            stmt.setString(1,menu.getFoodID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void main(String[] args) {
        MenuDA menuDA = new MenuDA();
        System.out.println(menuDA.findID());
    }
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("Connected");
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String findID(){
        
        String newID = null;
        ArrayList<Menu> menu = getRecord();
        int lastID = 0;
        
        if(menu.isEmpty()){
            return "F001";
        }
        else{
            int idCheck = Integer.parseInt(menu.get(0).getFoodID().substring(1));
            lastID = Integer.parseInt(menu.get(menu.size()-1).getFoodID().substring(1));
        
        for(int i = 0 ; i < menu.size() ; i++){
            if(idCheck != Integer.parseInt(menu.get(i).getFoodID().substring(1))){
                newID = String.format("F%03d", idCheck);
                break;
            }
            else if(idCheck == lastID){
                newID = String.format("F%03d", lastID+1);
                break;
            }
            idCheck++;
        }
        }
        
        
        return newID;
    }
    
}
