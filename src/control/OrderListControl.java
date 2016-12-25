/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.OrderListDA;
import domain.Orderlist;
import java.util.ArrayList;

/**
 *
 * @author Guang
 */
public class OrderListControl {
    OrderListDA orderListDA;
    
    public OrderListControl(){
        orderListDA = new OrderListDA();
    }
    
    public ArrayList<Orderlist> getRecord(String orderID){
        return orderListDA.getRecord(orderID);
    }
    
    public Orderlist getRecord(String orderID , String FoodID){
        return orderListDA.getRecord(orderID, FoodID);
    }
    
    public void addOrderList(String orderID,Orderlist orderlist){
        orderListDA.addOrderList(orderID, orderlist);
    }
    
    public void updateOrderList(String orderID,Orderlist orderlist){
        orderListDA.updateOrderList(orderID, orderlist);
    }
    
    public void deleteOrderList(String orderID,Orderlist orderlist){
        orderListDA.deleteOrderList(orderID, orderlist);
    }
    
    
}
