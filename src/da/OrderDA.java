/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package da;

import domain.Member;
import domain.Menu;
import domain.Order;
import domain.Orderlist;
import domain.Staff;
import domain.Tables;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class OrderDA {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String tableName = "Orders";
    private String user = "assignment";
    private String password = "assignment";
    private Connection conn;
    private PreparedStatement stmt;
    private CustomerDA memberDA;
    private OrderListDA orderlistDA;
    private TableListDA tablelistDA;
    private StaffDA staffDA;
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    private final DateFormat year = new SimpleDateFormat("yyyy");

    public OrderDA() {
        memberDA = new CustomerDA();
        orderlistDA = new OrderListDA();
        tablelistDA = new TableListDA();
        staffDA = new StaffDA();
        createConnection();
    }

    public ArrayList<Order> getRecord() {
        String queryStr = "SELECT * FROM " + tableName + " Order by OrderID DESC";

        ArrayList<Order> order = new ArrayList<Order>();

        try {
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Staff staff = null;
                Member member = memberDA.getRecord(rs.getString("MEMBERID"));
                ArrayList<Orderlist> orderlist = orderlistDA.getRecord(rs.getString("orderID"));
                Tables table = tablelistDA.getRecord(rs.getInt("tableno"));

                order.add(new Order(rs.getString("orderID"), table, orderlist, staff, member, rs.getString("PaymentType"), rs.getInt("CardLast4Digit")));

            }

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return order;
    }

    public ArrayList<Order> getRecord(Timestamp time, String Option) {
        String queryStr ="";
        if (Option.equals(OrderDA.MONTH)) {
            queryStr = "SELECT * FROM " + tableName + " WHERE year(CHECKOUTTIME) = ? and month(CHECKOUTTIME) = ? Order by OrderID DESC";
        } else if(Option.equals(OrderDA.YEAR)){
            queryStr = "SELECT * FROM " + tableName + " WHERE year(CHECKOUTTIME) = ? Order by OrderID DESC";
        }
        ArrayList<Order> order = new ArrayList<Order>();

        try {
            
            String yearString = year.format(new Date(time.getTime()));
            stmt = conn.prepareStatement(queryStr);
            if(Option.equals(OrderDA.MONTH))
                stmt.setInt(2, time.getMonth()+1);
            
            stmt.setInt(1, Integer.parseInt(yearString));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Staff staff = null;
                Member member = memberDA.getRecord(rs.getString("MEMBERID"));
                ArrayList<Orderlist> orderlist = orderlistDA.getRecord(rs.getString("orderID"));
                Tables table = tablelistDA.getRecord(rs.getInt("tableno"));

                order.add(new Order(rs.getString("orderID"), table, orderlist, staff, member, rs.getString("PaymentType"),rs.getTimestamp("CheckOutTime"), rs.getInt("CardLast4Digit")));

            }

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return order;

    }

    public Order getRecord(String orderID) {
        String queryStr = "SELECT * FROM " + tableName + " WHERE OrderID = ?";

        Order order = null;

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Staff staff = staffDA.getRecord(rs.getString("StaffID"));
                Member member = memberDA.getRecord(rs.getString("MEMBERID"));
                ArrayList<Orderlist> orderlist = orderlistDA.getRecord(orderID);
                Tables table = tablelistDA.getRecord(rs.getInt("tableno"));

                order = new Order(orderID, table, orderlist, staff, member, rs.getString("PaymentType"), rs.getInt("CardLast4Digit"));

            }

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return order;
    }

    public void createOrder(Order order) {
        String queryStr = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, order.getOrderID());
            stmt.setInt(2, order.getTable().getTableNo());
            stmt.setString(3, order.getStaff().getStaffID());
            stmt.setString(4, order.getMember().getMemberID());
            stmt.setString(5, order.getPaymentType());
            stmt.setTimestamp(6, order.getCheckOutTime());
            stmt.setInt(7, order.getCardLast4Digit());
            stmt.executeUpdate();

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateOrder(Order order) {
        String queryStr = "UPDATE " + tableName + " set TableNo = ?, StaffID = ?, MemberID = ?, paymenttype = ?, checkouttime = ?, cardLast4Digit = ? where ORDERID = ? ";

        String staffID = null;
        String customerID = null;
        try {
            if (order.getStaff() == null) {
                staffID = null;
            } else {
                staffID = order.getStaff().getStaffID();
            }

            if (order.getMember() == null) {
                customerID = null;
            } else {
                customerID = order.getMember().getMemberID();
            }

            stmt = conn.prepareStatement(queryStr);
            stmt.setInt(1, order.getTable().getTableNo());
            stmt.setString(2, staffID);
            stmt.setString(3, customerID);
            stmt.setString(4, order.getPaymentType());
            stmt.setTimestamp(5, order.getCheckOutTime());
            stmt.setInt(6, order.getCardLast4Digit());
            stmt.setString(7, order.getOrderID());
            stmt.executeUpdate();

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteOrder(Order order) {
        String queryStr = "delete from " + tableName + " where ORDERID = ? ";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, order.getOrderID());
            stmt.executeUpdate();

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateCustomerInfo(String orderID, String memberID) {
        String queryStr = "UPDATE " + tableName + " set MemberID = ? where ORDERID = ? ";

        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, memberID);
            stmt.setString(2, orderID);
            stmt.executeUpdate();

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        /* test Data
        OrderDA orderDA = new OrderDA();
        String id = new String(orderDA.findNewID());
        System.out.println(orderDA.findNewID());
        Order order = new Order(id,new Tables(9,0,null),null,new Staff("S004",null,"123456",null,null,null,null,null,null,null),new Member("C0000007",null,0,null,null,null,null,null,null,null),"CARD", 1234);
        orderDA.createOrder(order);
        System.out.println(orderDA.getRecord(id));
        orderDA.updateOrder(order);
        System.out.println(orderDA.getRecord(id));
        orderDA.updateCustomerInfo(id, "C0000001");
        System.out.println(orderDA.getRecord(id));
        orderDA.deleteOrder(order);
        System.out.println(orderDA.getRecord(id));*/
    }

    public String findNewID() {
        ArrayList<Order> order = getRecord();
        String newID = null;
        String lastID = order.get(0).getOrderID();
        int index = 0;

        if (order.size() == 0) {
            index = 0;
        } else if (order.size() < 60) {
            index = order.size() - 1;
        } else {
            index = 59;
        }

        int idCheck = Integer.parseInt(order.get(index).getOrderID());

        for (int i = index; i >= 0; i--) {
            if (idCheck != Integer.parseInt(order.get(i).getOrderID())) {
                newID = String.format("%010d", idCheck);
                break;
            } else if (idCheck == Integer.parseInt(lastID)) {
                newID = String.format("%010d", Integer.parseInt(lastID) + 1);
                break;
            }
            idCheck++;
        }

        return newID;
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("Connected");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
