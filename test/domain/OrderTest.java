/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author haoguang
 */
public class OrderTest {

    private static Order order;
    private static String orderID = "1001";
    private static Staff staff = new Staff();
    private static Member member = new Member();
    private static ArrayList<Orderlist> orderlist = new ArrayList<>();
    private static String paymentType = "Credit Card";
    private static int last4digit = 1234;
    private static Tables table = new Tables(2, 4, "Occur");

    public OrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        order = new Order(orderID, table, orderlist, staff, member,paymentType, last4digit);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("this is set up");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrderID method, of class Order.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        Order instance = order;
        String expResult = orderID;
        String result = instance.getOrderID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderID method, of class Order.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        String orderID = "1002";
        Order instance = order;
        instance.setOrderID(orderID);
        assertEquals(orderID, instance.getOrderID());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTable method, of class Order.
     */
    @Test
    public void testGetTable() {
        System.out.println("getTable");
        Order instance = order;
        Tables expResult = table;
        Tables result = instance.getTable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setTable method, of class Order.
     */
    @Test
    public void testSetTable() {
        System.out.println("setTable");
        Tables table = new Tables(2, 4, "Occur");;
        Order instance = new Order();
        instance.setTable(table);
        assertEquals(table, instance.getTable());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getOrderlist method, of class Order.
     */
    @Test
    public void testGetOrderlist() {
        System.out.println("getOrderlist");
        Order instance = order;
        ArrayList<Orderlist> expResult = orderlist;
        ArrayList<Orderlist> result = instance.getOrderlist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setOrderlist method, of class Order.
     */
    @Test
    public void testSetOrderlist() {
        System.out.println("setOrderlist");
        ArrayList<Orderlist> orderlist = null;
        Order instance = new Order();
        instance.setOrderlist(orderlist);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getStaff method, of class Order.
     */
    @Test
    public void testGetStaff() {
        System.out.println("getStaff");
        Order instance = order;
        Staff expResult = staff;
        Staff result = instance.getStaff();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setStaff method, of class Order.
     */
    @Test
    public void testSetStaff() {
        System.out.println("setStaff");
        Staff staff = this.staff;
        Order instance = new Order();
        instance.setStaff(staff);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMember method, of class Order.
     */
    @Test
    public void testGetMember() {
        System.out.println("getMember");
        Order instance = order;
        Member expResult = member;
        Member result = instance.getMember();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setMember method, of class Order.
     */
    @Test
    public void testSetMember() {
        System.out.println("setMember");
        Member member = null;
        Order instance = new Order();
        instance.setMember(member);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentType method, of class Order.
     */
    @Test
    public void testGetPaymentType() {
        System.out.println("getPaymentType");
        Order instance = order;
        String expResult = paymentType;
        String result = instance.getPaymentType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setPaymentType method, of class Order.
     */
    @Test
    public void testSetPaymentType() {
        System.out.println("setPaymentType");
        String paymentType = "Cash";
        Order instance = new Order();
        instance.setPaymentType(paymentType);
        assertEquals(paymentType, instance.getPaymentType());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCardLast4Digit method, of class Order.
     */
    @Test
    public void testGetCardLast4Digit() {
        System.out.println("getCardLast4Digit");
        Order instance = order;
        int expResult = 1234;
        int result = instance.getCardLast4Digit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    
    }

    /**
     * Test of setCardLast4Digit method, of class Order.
     */
    @Test
    public void testSetCardLast4Digit() {
        System.out.println("setCardLast4Digit");
        int cardLast4Digit = 2468;
        Order instance = new Order();
        instance.setCardLast4Digit(cardLast4Digit);
        assertEquals(cardLast4Digit, instance.getCardLast4Digit());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCheckOutTime method, of class Order.
     */
    @Test
    public void testGetCheckOutTime() {
        System.out.println("getCheckOutTime");
        Order instance = order;
        Timestamp expResult = null;
        Timestamp result = instance.getCheckOutTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setCheckOutTime method, of class Order.
     */
    @Test
    public void testSetCheckOutTime() {
        System.out.println("setCheckOutTime");
        Timestamp checkOutTime = null;
        Order instance =order;
        instance.setCheckOutTime(checkOutTime);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getGstRate method, of class Order.
     */
    @Test
    public void testGetGstRate() {
        System.out.println("getGstRate");
        double expResult = 0.06;
        double result = Order.getGstRate();
        assertEquals(expResult, result, 0.06);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setGstRate method, of class Order.
     */
    @Test
    public void testSetGstRate() {
        System.out.println("setGstRate");
        double gstRate = 0.06;
        Order.setGstRate(gstRate);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of addOrder method, of class Order.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Orderlist orders = new Orderlist(new Menu("F001", "Fried rice", "mixed with rice and vege", 4.55, 'M'),6,"abc");
        Order instance = order;
        instance.addOrder(orders);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of modifyOrder method, of class Order.
     */
    @Test
    public void testModifyOrder() {
        System.out.println("modifyOrder");
        String FoodID = "F001";
        int quantity = 2;
        Order instance = order;
        instance.modifyOrder(FoodID, quantity);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of DeleteOrder method, of class Order.
     */
    @Test
    public void testDeleteOrder() {
        System.out.println("DeleteOrder");
        String FoodID = "F001";
        Order instance = new Order();
        instance.DeleteOrder(FoodID);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(instance.getOrderlist());
    }

    /**
     * Test of calculatePayment method, of class Order.
     */
    @Test
    public void testCalculatePayment() {
        System.out.println("calculatePayment");
        Order order = this.order;
        double expResult = 0.0;
        double result = Order.calculatePayment(order);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of calculateGST method, of class Order.
     */
    @Test
    public void testCalculateGST() {
        System.out.println("calculateGST");
        double price = 100;
        double expResult = 6;
        double result = Order.calculateGST(price);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of calculatePoints method, of class Order.
     */
    @Test
    public void testCalculatePoints() {
        System.out.println("calculatePoints");
        double price = 100;
        int expResult = 1;
        int result = Order.calculatePoints(price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

}
