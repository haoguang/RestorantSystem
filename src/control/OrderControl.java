/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.OrderDA;
import domain.Order;
import java.util.ArrayList;

public class OrderControl {
    OrderDA orderDA;
    
    public OrderControl(){
        orderDA = new OrderDA();
    }
    
    public ArrayList<Order> getRecord(){
        return orderDA.getRecord();
    }
    
    public Order getRecord(String orderID){
        return orderDA.getRecord(orderID);
    }
    
    public void createOrder(Order order){
        orderDA.createOrder(order);
    }
    
    public void updateOrder(Order order){
        orderDA.updateOrder(order);
    }
    
    public void deleteOrder(Order order){
        orderDA.deleteOrder(order);
    }
    
    public void updateCustomerInfo(String orderID, String memberID){
        orderDA.updateCustomerInfo(orderID, memberID);
    }
    
    public String findNewID(){
        return orderDA.findNewID();
    }
}
