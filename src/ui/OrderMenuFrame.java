/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import control.CustomerControl;
import control.MenuControl;
import control.OrderListControl;
import da.OrderDA;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.ArrayList;
import domain.Menu;
import domain.Order;
import domain.Orderlist;
import java.awt.Dialog.ModalityType;
import javax.swing.border.TitledBorder;

public class OrderMenuFrame extends JFrame {
    
    private String orderID;
    private ArrayList<Menu> food; //use to keep the food item got from the data base
    private ArrayList<JButton> foodButton = new ArrayList<JButton>(); // button to create orderlist; each button is linked to the food object;
    private JTextField searchField = new JTextField();
    
    private JButton cancelButton = new JButton("Cancel");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete ");
    private JButton addButton = new JButton("Add");
    private JButton addCustomer = new JButton("Add Customer");;
    private OrderListControl orderListControl;
    private JTable orderTable;
    private JPanel customerPanel = new JPanel();
    private JPanel tableLayout = new JPanel();
    private ArrayList<Orderlist> orderlist;
    private JButton changeCustomer = new JButton("Change");
    Object[] columnName = {"Food ID", "Food Name", "Quantity", "Remarks"};


    public OrderMenuFrame(Order order) {
        
        setLayout(new BorderLayout());
        this.orderID = order.getOrderID();
        orderListControl = new OrderListControl();
        
        
        food = new MenuControl().getRecord();
        String[] categorizeNames = {"Appetizer", "Main Courses", "Side Dishes", "Dessert", "Beverage", "Wine"};
        int[] itemCount = new int[categorizeNames.length];
        int columnItem = 4;
        
        //count items for each categorize
        for(int i=0 ; i<food.size();i++){
            switch(food.get(i).getCategorise()){
                case 'A':
                    itemCount[0]++;
                    break;
                case 'M':
                    itemCount[1]++;
                    break;
                case 'S':
                    itemCount[2]++;
                    break;
                    
                case 'D':
                    itemCount[3]++;
                    break;
                case 'B':
                    itemCount[4]++;
                    break;
                case 'W':
                    itemCount[5]++;
            }
        }
        

        //Panel for Every categorize of food
        JPanel[] foodCategorise = new JPanel[categorizeNames.length];
        //set relative row for each panel
        int rowNeed;
        for(int i=0 ; i < categorizeNames.length ; i++){
            
            if(itemCount[i]%columnItem==0)
                rowNeed = itemCount[i]/columnItem;
            else 
                rowNeed = itemCount[i]/columnItem+1;
            foodCategorise[i] = new JPanel(new GridLayout(rowNeed,columnItem , 10,10));
            foodCategorise[i].setBorder(new TitledBorder(categorizeNames[i]));
        }
        JPanel menuPanel = new JPanel(new GridLayout(categorizeNames.length,1));
        
        
         JScrollPane scrollPane = new JScrollPane(menuPanel);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         scrollPane.getVerticalScrollBar().setUnitIncrement(16);//adjust scroll speed
         

        

        

        JButton temp;//use to create a multiple row button of food and then add into the arraybutton
        for (int i = 0; i < food.size(); i++) {
            temp = new JButton();
            temp.setLayout(new BorderLayout());//set layout for button to make 3 row label for the button
            temp.add(BorderLayout.NORTH, new JLabel(food.get(i).getFoodID(), SwingConstants.CENTER));
            temp.add(BorderLayout.CENTER, new JLabel(food.get(i).getName(), SwingConstants.CENTER));
            temp.add(BorderLayout.SOUTH, new JLabel(String.format("RM %.2f", food.get(i).getPrice()), SwingConstants.CENTER));
            temp.setActionCommand(String.format("%d", i));
            temp.setPreferredSize(new Dimension(180, 70));
            

            //set color depend on the categorize of the food
            switch (food.get(i).getCategorise()) {
                case 'A':
                    temp.setBackground(new Color(255, 20, 147));
                    foodCategorise[0].add(temp);
                    break;
                case 'M':
                    temp.setBackground(new Color(255, 69, 0));
                    foodCategorise[1].add(temp);
                    break;
                case 'S':
                    temp.setBackground(Color.YELLOW);
                    foodCategorise[2].add(temp);
                    break;
                case 'D':
                    temp.setBackground(Color.GREEN);
                    foodCategorise[3].add(temp);
                    break;
                case 'B':
                    temp.setBackground(new Color(0, 191, 255));
                    foodCategorise[4].add(temp);
                    break;
                case 'W':
                    temp.setBackground(new Color(57, 100, 195));
                    foodCategorise[5].add(temp);

            }
            //to make the button easy to reference
            foodButton.add(temp);
        }
        
        //add null to remainding grid space
        int nullNeeded = 0;
        for(int i=0; i<categorizeNames.length ; i++ ){
            if(itemCount[i]%columnItem != 0)
                nullNeeded = columnItem - (itemCount[i]%columnItem);
            else
                nullNeeded = columnItem - itemCount[i];
            for(int j=0 ; j<nullNeeded ; j ++)
                foodCategorise[i].add(new JPanel());
        }

        //add menubuttonlistener to all of the button
        for (int i = 0; i < foodButton.size(); i++) {
            foodButton.get(i).addActionListener(new MenuButtonListener());
        }
        
        //add food panels into the menuPanel
        for(int i = 0 ; i <categorizeNames.length ; i++ ){
            menuPanel.add(foodCategorise[i]);
        }


        /*
         JPanel to take order from customer
         -provide table information
         -can use to add customer name
         -orderlist
         */
        JPanel orderMenu = new JPanel(new BorderLayout());
        JLabel tableNo = new JLabel("Table No: " + order.getTable().getTableNo(), SwingConstants.CENTER);
        tableNo.setFont(new Font("Tw Cen MT Condensed Extra Bold", 0, 60));

        JPanel tableInformation = new JPanel(new GridLayout(3, 1));
        tableInformation.add(tableNo);
        changeCustomer.addActionListener(new ResetCustomerListener());
        addCustomer.addActionListener(new ChangeCustomerListener());
        findCustomer();
        tableInformation.add(customerPanel);
        tableInformation.add(searchField);

        setDataToTable();
        
        
        editButton.addActionListener(new EditButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        
        JPanel buttons = new JPanel(new GridLayout(1, 3, 2, 2));
        buttons.add(editButton);
        buttons.add(deleteButton);
        buttons.add(cancelButton);
        
        orderMenu.add(tableInformation, BorderLayout.NORTH);
        orderMenu.add(tableLayout, BorderLayout.CENTER);
        orderMenu.add(buttons, BorderLayout.SOUTH);
        
        add(scrollPane, BorderLayout.CENTER);
        add(orderMenu, BorderLayout.EAST);
        
        
        
        

    }

    private class MenuButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           
            addOrderFrame addOrder = new addOrderFrame(orderID,food.get(Integer.parseInt(e.getActionCommand())));
            addOrder.setTitle("Add new order");
            addOrder.setVisible(true);
            addOrder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //addOrder.setAlwaysOnTop(true);
            addOrder.addWindowListener(new DisableWindowListener());
         
             
        }

    }
    
    private class EditButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Orderlist orderlist;
            String foodid;
            
            try{
            if(orderTable.getSelectedRow()==-1)
                JOptionPane.showMessageDialog(null,"Please Select an Item");
            else{
                foodid = (String)orderTable.getValueAt(orderTable.getSelectedRow(),0);
                
                orderlist = orderListControl.getRecord(orderID , foodid);
            
                EditOrderFrame editOrder = new EditOrderFrame(orderID,orderlist);
                editOrder.setTitle("Add new order");
                editOrder.setVisible(true);
                editOrder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                editOrder.setModal(true);
                editOrder.setAlwaysOnTop(true);
                editOrder.setModalityType(ModalityType.APPLICATION_MODAL);
                editOrder.addWindowListener(new DisableWindowListener());
            }
        
        }catch(NullPointerException nullExp){
            JOptionPane.showMessageDialog(null, "Row selected was empty. Please select again!", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
    
    private class DeleteButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Orderlist orderlist;
            String foodid;
            
            try{
            if(orderTable.getSelectedRow()==-1)
                JOptionPane.showMessageDialog(null,"Please Select an Item");
            else{
                foodid = (String)orderTable.getValueAt(orderTable.getSelectedRow(),0);
                
                orderlist = orderListControl.getRecord(orderID , foodid);
            
                int choice = JOptionPane.showConfirmDialog(null, "Comfirm to Delete the following Item?\n  " 
                        + orderlist.getFood().getFoodID() +"\n\t"+ orderlist.getFood().getName(), "Delete Food Item", JOptionPane.YES_NO_OPTION);
                
                if(choice == JOptionPane.YES_OPTION)
                    orderListControl.deleteOrderList(orderID, orderlist);
                
                setDataToTable();
                
            }
        
        }catch(NullPointerException nullExp){
            JOptionPane.showMessageDialog(null, "Row selected was empty. Please select again!", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        }
        }
        
    }
    
    
    private class DisableWindowListener implements WindowListener{
        
        @Override
        public void windowOpened(WindowEvent e) {
            setEnabled(false);
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            setEnabled(true);
        }
        
        @Override
        public void windowClosed(WindowEvent e) {
            setEnabled(true);
            toFront();
            setDataToTable();
            findCustomer();
            
            }
            
        
    }//used to disable parent window when child window is open.
    
    private class CancelButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
        
    }
    
    private class ChangeCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerFinder changeCustomer = new CustomerFinder(orderID);
            changeCustomer.setTitle("Add new order");
                changeCustomer.setVisible(true);
                changeCustomer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                changeCustomer.setModal(true);
                changeCustomer.setAlwaysOnTop(true);
                changeCustomer.setModalityType(ModalityType.APPLICATION_MODAL);
                changeCustomer.addWindowListener(new DisableWindowListener());
                
        }
        
    }
    
    private class ResetCustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderDA orderDA = new OrderDA();
            
            orderDA.updateCustomerInfo(orderID, null);
            findCustomer();
        }
        
    }

    public static void main(String[] args) {
        /*
        OrderMenuFrame orderMenu = new OrderMenuFrame("0000000001");
        orderMenu.setTitle("Order Menu");
        orderMenu.setSize(1270, 720);
        orderMenu.setLocationRelativeTo(null);
        orderMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderMenu.setVisible(true);
              */
    }
    
    private void setDataToTable(){
        orderlist = orderListControl.getRecord(orderID);
        
        tableLayout.removeAll();
        tableLayout.updateUI();
        tableLayout.repaint();
        
        Object [][]  tableElements= new Object[orderlist.size()][columnName.length];
        
        for(int i=0 ; i<orderlist.size() ; i ++){
            for(int j=0 ; j<columnName.length ; j++){
                switch(j){
                    case 0:
                        tableElements[i][j]=orderlist.get(i).getFood().getFoodID();
                        break;
                    case 1:
                        tableElements[i][j]=orderlist.get(i).getFood().getName();
                        break;
                    case 2:
                        tableElements[i][j]=orderlist.get(i).getQuantity();
                        break;
                    case 3:
                        tableElements[i][j]=orderlist.get(i).getRemark();
                }
                
            }
        }
        
        orderTable = new JTable(tableElements, columnName) {
            //use to make cell uneditable but selectable for user
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderTable.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane tableOrder = new JScrollPane(orderTable);
        tableOrder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableOrder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        tableLayout.add(tableOrder);
        
    }
    
    private void findCustomer(){
        OrderDA orderDA = new OrderDA();
        
        Order order = orderDA.getRecord(orderID);
        
        try {
        if(order.getMember() == null || order.getMember().getMemberID() == ""){
            customerPanel.removeAll();
            customerPanel.updateUI();
            customerPanel.repaint();
            customerPanel.add(addCustomer);
        }
        else{
            customerPanel.removeAll();
            customerPanel.updateUI();
            customerPanel.repaint();
            
            JPanel memberInfo = new JPanel(new BorderLayout(10,1000));
            JLabel memberName = new JLabel(order.getMember().getFirstName());
            memberInfo.add(memberName, BorderLayout.CENTER);
            memberInfo.add(changeCustomer, BorderLayout.EAST);
            customerPanel.add(memberInfo);
        }
            
        }catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Order does not exist!!", "NullPointerException", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
}
    
