/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class MenuTest {
    private static Menu menu ;
    public MenuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        menu = new Menu("F001", "Nasi Lemak", "malay famous food", 2.0, 'M');
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
     * Test of getFoodID method, of class Menu.
     */
    @Test
    public void testGetFoodID() {
        System.out.println("getFoodID");
        Menu instance = menu;
        String expResult = "F001";
        String result = instance.getFoodID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setFoodID method, of class Menu.
     */
    @Test
    public void testSetFoodID() {
        System.out.println("setFoodID");
        String foodID = "F002";
        Menu instance = new Menu();
        instance.setFoodID(foodID);
        assertEquals(foodID, instance.getFoodID());
        // TODO review the generated test code and remove the default call to fail.
    
    }

    /**
     * Test of getName method, of class Menu.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Menu instance = menu;
        String expResult = "Nasi Lemak";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setName method, of class Menu.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Nasi Dagang";
        Menu instance = menu;
        instance.setName(name);
        assertEquals("Nasi Dagang", menu.getName());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getDescription method, of class Menu.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Menu instance = menu;
        String expResult = "malay famous food";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of setDescription method, of class Menu.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Malaysia delisious food";
        Menu instance = new Menu();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("Malaysia delisious food", instance.getDescription());
    }

    /**
     * Test of getPrice method, of class Menu.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Menu instance = menu;
        double expResult = 2.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setPrice method, of class Menu.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 4.0;
        Menu instance = menu;
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(4.0, instance.getPrice(), 0.0);
    }

    /**
     * Test of getCategorise method, of class Menu.
     */
    @Test
    public void testGetCategorise() {
        System.out.println("getCategorise");
        Menu instance = menu;
        char expResult = 'M';
        char result = instance.getCategorise();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setCategorise method, of class Menu.
     */
    @Test
    public void testSetCategorise() {
        System.out.println("setCategorise");
        char categorise = 'W';
        Menu instance = new Menu();
        instance.setCategorise(categorise);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals('W', instance.getCategorise());
    }


}
