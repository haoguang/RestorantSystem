/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.GregorianCalendar;
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
public class StaffTest {
    private static Staff staff;
    public StaffTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        staff = new Staff("S001", null, "abc123","Eric", "Watson", "abc", "960523", new GregorianCalendar(1996, 5, 23), "eric@hotmail.com", "016532387");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStaffID method, of class Staff.
     */
    @Test
    public void testGetStaffID() {
        System.out.println("getStaffID");
        Staff instance = staff;
        String expResult = "S001";
        String result = instance.getStaffID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setStaffID method, of class Staff.
     */
    @Test
    public void testSetStaffID() {
        System.out.println("setStaffID");
        String staffID = "S002";
        Staff instance = new Staff();
        instance.setStaffID(staffID);
        String expResult = "S002";
        assertEquals(expResult, instance.getStaffID());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getJob method, of class Staff.
     */
    @Test
    public void testGetJob() {
        System.out.println("getJob");
        Staff instance = staff;
        Job expResult = null;
        Job result = instance.getJob();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setJob method, of class Staff.
     */
    @Test
    public void testSetJob() {
        System.out.println("setJob");
        Job job = new Job();
        Staff instance = new Staff();
        instance.setJob(job);
        assertEquals(job, instance.getJob());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getPassword method, of class Staff.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Staff instance = staff;
        String expResult = "abc123";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of changePassword method, of class Staff.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        String oldPassword = "abc123";
        String newPassword = "bcd321";
        Staff instance = staff;
        boolean expResult = true;
        boolean result = instance.changePassword(oldPassword, newPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

  
    
}
