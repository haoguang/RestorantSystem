/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Menu;
import domain.Orderlist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class OrderListDA {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String tableName = "OrderList";
    private String user = "assignment";
    private String password = "assignment";
    private Connection conn;
    private PreparedStatement stmt;
    private MenuDA menu;
    
    public OrderListDA(){
        menu = new MenuDA();
        createConnection();
    }
    
    public ArrayList<Orderlist> getRecord(String orderID){
        String queryStr = "SELECT * FROM "+tableName + " WHERE OrderID = ?";
        ArrayList<Orderlist> orderlist = new ArrayList<Orderlist>();
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, orderID);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Menu> menuRecord = menu.getRecord();
            Menu tempMenu = null;
            
            while(rs.next()){
                
                for(int i = 0 ; i< menuRecord.size(); i++){
                    if(menuRecord.get(i).getFoodID().equals(rs.getString("FoodID")))
                        tempMenu = menuRecord.get(i);
                    
                }
                
                Orderlist tempOrderlist = new Orderlist(tempMenu,rs.getInt("quantity"),rs.getString("remark"));
                orderlist.add(tempOrderlist);
            }
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return orderlist;
    }
    
    public Orderlist getRecord(String orderID , String foodID){
        String queryStr = "SELECT * FROM "+tableName + " WHERE OrderID = ? AND FOODID = ?";
        Orderlist orderlist = null;
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, orderID);
            stmt.setString(2, foodID);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Menu> menuRecord = menu.getRecord();
            Menu tempMenu = null;
            
            if(rs.next()){
                
                for(int i = 0 ; i< menuRecord.size(); i++){
                    if(menuRecord.get(i).getFoodID().equals(rs.getString("FoodID")))
                        tempMenu = menuRecord.get(i);
                    
                }
                
                orderlist = new Orderlist (tempMenu,rs.getInt("quantity"),rs.getString("remark"));
            }
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        return orderlist;
    }
    
    public void addOrderList(String orderID,Orderlist orderlist){
        String queryStr = "INSERT INTO "+tableName +" VALUES (?,?,?,?)";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1,orderID );
            stmt.setString(2, orderlist.getFood().getFoodID());
            stmt.setInt(3, orderlist.getQuantity());
            stmt.setString(4, orderlist.getRemark());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateOrderList(String orderID,Orderlist orderlist){
        String queryStr = "UPDATE "+tableName+" set quantity = ?, remark = ? where ORDERID = ? AND FOODID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setInt(1,orderlist.getQuantity() );
            stmt.setString(2, orderlist.getRemark());
            stmt.setString(3, orderID);
            stmt.setString(4, orderlist.getFood().getFoodID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteOrderList(String orderID , Orderlist orderlist){
        String queryStr = "delete from "+tableName+" where ORDERID = ? AND FOODID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);  
            stmt.setString(1,orderID);
            stmt.setString(2,orderlist.getFood().getFoodID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("Connected");
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void main(String[] args) {
        /* Test DA function
        OrderListDA testing = new OrderListDA();
        System.out.println(testing.getRecord(new String("0000000001")));
        testing.addOrderList("0000000001", new Orderlist(new Menu("F001",null,null,20.2,'D'),3,"abc"));
        System.out.println(testing.getRecord(new String("0000000001")));
        testing.updateOrderList("0000000001", new Orderlist(new Menu("F001",null,null,20.2,'D'),1,"F**K"));
        System.out.println(testing.getRecord(new String("0000000001")));
        testing.deleteOrderList("0000000001", new Orderlist(new Menu("F001",null,null,20.2,'D'),3,"abc"));
        System.out.println(testing.getRecord(new String("0000000001")));
          */     
    }
    
}
