/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import control.CustomerControl;
import control.OrderControl;
import domain.Member;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CustomerFinder extends JDialog{

    private String orderID;
    private JTable customerTable;
    private ArrayList<Member> member;
    private CustomerControl customerControl = new CustomerControl();
    private Object[][] tableElement;
    private Object[] columnNames = {"Member ID","First Name","Last Name","Address","IC",  "Email", "Phone No","Point"};
    private TableRowSorter<TableModel> sorter ;
    
    private JTextField searchField = new JTextField(80);
    private JButton comfirmButton = new JButton("Comfirm");
      
    
    public CustomerFinder(String orderID){
        this.orderID=orderID;
        setLayout(new BorderLayout(40,20));
        setSize(1000,500);
        
        
        member = customerControl.getRecord();
        tableElement = new Object[member.size()][columnNames.length];
        
        for(int row = 0 ; row < member.size(); row ++){
            for(int col = 0 ; col < columnNames.length ; col++){
                switch(col){
                    case 0:
                        tableElement[row][col] = member.get(row).getMemberID();
                        break;
                    case 1:
                        tableElement[row][col] = member.get(row).getFirstName();
                        break;
                    case 2:
                        tableElement[row][col] = member.get(row).getLastName();
                        break;
                    case 3:
                        tableElement[row][col] = member.get(row).getAddress();
                        break;
                    case 4:
                        tableElement[row][col] = member.get(row).getIC();
                        break;
                    case 5:
                        tableElement[row][col] = member.get(row).getEmail();
                        break;
                    case 6:
                        tableElement[row][col] = member.get(row).getPhoneNo();
                        break;
                    case 7:
                        tableElement[row][col] = member.get(row).getPoint();
                        
                }
            }
        }
        
        customerTable = new JTable(tableElement, columnNames){
            //use to make cell uneditable but selectable for user
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.getTableHeader().setReorderingAllowed(false);
        
        sorter = new TableRowSorter<TableModel>(customerTable.getModel());
        
        customerTable.setRowSorter(sorter);
        
        JPanel searchBar = new JPanel(new BorderLayout());
        searchBar.add(new Label("Search Customer : "),BorderLayout.WEST);
        searchBar.add(searchField,BorderLayout.CENTER);
        
        searchField.getDocument().addDocumentListener(new searchDocumentListener());
        comfirmButton.addActionListener(new comfirmButtonListener());
        
        add(searchBar,BorderLayout.NORTH);
        add(new JScrollPane(customerTable),BorderLayout.CENTER);
        add(comfirmButton, BorderLayout.SOUTH);
        
    }
    
    private class searchDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));
        }
        
    }
    
    private class comfirmButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderControl ordercontrol = new OrderControl();
            String customerID;
            try{
            if(customerTable.getSelectedRow()==-1)
                JOptionPane.showMessageDialog(null,"Please Select an Item");
            else{
                customerID = (String)customerTable.getValueAt(customerTable.getSelectedRow(),0);
                
                ordercontrol.updateCustomerInfo(orderID, customerID);
                dispose();

                
            }
        
        }catch(NullPointerException nullExp){
            JOptionPane.showMessageDialog(null, "Row selected was empty. Please select again!", "NullPointerException", JOptionPane.ERROR_MESSAGE);
        }
            
        }
        
    }
    
    public static void main(String[] args) {
        CustomerFinder frame = new CustomerFinder("0000000001");
        
        frame.setTitle("TestFindCustomer");
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    
}
