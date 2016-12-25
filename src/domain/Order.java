
package domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import Calculation.RoundOff;

public class Order {
    private String orderID;
    private Tables table;
    private ArrayList<Orderlist> orderlist;
    private Staff staff;
    private Member member;
    private String paymentType;
    private Timestamp checkOutTime;

    public Order(String orderID, Tables table, ArrayList<Orderlist> orderlist, Staff staff, Member member, String paymentType, Timestamp checkOutTime, int cardLast4Digit) {
        this.orderID = orderID;
        this.table = table;
        this.orderlist = orderlist;
        this.staff = staff;
        this.member = member;
        this.paymentType = paymentType;
        this.checkOutTime = checkOutTime;
        this.cardLast4Digit = cardLast4Digit;
    }
    private int cardLast4Digit;
    private static double gstRate = 0.06;

    public Order() {
        this.table = new Tables();
        this.member = new Member();
        this.staff = new Staff();
        this.orderlist = new ArrayList<Orderlist>();
    }

    public Order(String orderID, Tables table, ArrayList<Orderlist> orderlist, Staff staff, Member member, String paymentType, int cardLast4Digit) {
        this.orderID = orderID;
        this.table = table;
        this.orderlist = orderlist;
        this.staff = staff;
        this.member = member;
        this.paymentType = paymentType;
        this.cardLast4Digit = cardLast4Digit;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public ArrayList<Orderlist> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<Orderlist> orderlist) {
        this.orderlist = orderlist;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getCardLast4Digit() {
        return cardLast4Digit;
    }

    public void setCardLast4Digit(int cardLast4Digit) {
        this.cardLast4Digit = cardLast4Digit;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public static double getGstRate() {
        return gstRate;
    }

    public static void setGstRate(double gstRate) {
        Order.gstRate = gstRate;
    }
    
    public void addOrder(Orderlist orders){
        boolean duplicate = false;
        for(int i=0 ; i < orderlist.size() ; i++){
            if(orders.getFood().getFoodID().equals(orderlist.get(i).getFood().getFoodID()))
                duplicate = true;
        }
        
        if(duplicate == false)
            orderlist.add(orders);
    }
    
    public void modifyOrder(String FoodID, int quantity){
        for(int i=0 ; i < orderlist.size() ; i++){
            if(orderlist.get(i).getFood().getFoodID().equalsIgnoreCase(FoodID))
                orderlist.get(i).setQuantity(quantity);
        }
    }
    
    public void DeleteOrder(String FoodID){
        for(int i=0 ; i < orderlist.size() ; i++){
            if(orderlist.get(i).getFood().getFoodID().equalsIgnoreCase(FoodID)){
                orderlist.remove(i);
            }
        }
    }
    
    public static double calculatePayment(Order order){
        double grandTotal= 0,totalPrice = 0;
        
        for(int i = 0 ;i < order.getOrderlist().size() ;i++){

                totalPrice += order.getOrderlist().get(i).getFood().getPrice()*order.getOrderlist().get(i).getQuantity();

        }
        RoundOff roundOff= new RoundOff(totalPrice + calculateGST(totalPrice));
        grandTotal = roundOff.getAfterRoundOffValue();
         
         
        return grandTotal;
    }
    
    public static double calculateGST(double price){
        return price * gstRate;
    }
    
    public static int calculatePoints(double price){
        return (int) (price * 0.01);
    }
    
    
    
}


