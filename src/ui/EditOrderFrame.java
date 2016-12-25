/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import control.OrderListControl;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import domain.Menu;
import domain.Orderlist;

public class EditOrderFrame extends JDialog{
    private String orderID;
    private Orderlist orderlist;
    private int quantity;
    private String remark;
    private OrderListControl orderListControl;
    
    private ImageIcon addIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/add.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon minusIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/minus.png")).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
    private JButton addButton = new JButton(addIcon);
    private JButton minusButton = new JButton(minusIcon);
    private JTextField quantityArea = new JTextField(2);
    private JTextArea remarkArea = new JTextArea(6,20);
    private JLabel foodID;
    private JLabel foodName;
    private JTextArea foodDescription;
    private JLabel quantityLabel;
    private JLabel remarkLabel = new JLabel("Remark : ");
    private GridBagConstraints c = new GridBagConstraints();
    private Font font1 = new Font("Bauhaus 93",0,35);
    private Font font2 = new Font("Matura MT Script Capitals",3,20);
    private Font font3 = new Font("Matura MT Script Capitals",0,16);
    
    private JButton comfirmButton = new JButton("Comfirm");
    private JButton cancelButton = new JButton("Cancel");
    
    public EditOrderFrame(String orderID, Orderlist orderlist){
        this.orderID = orderID;
        this.orderlist = orderlist;
        orderListControl = new OrderListControl();
        
        setSize(500,500);
        setResizable(false);
        setLayout(new BorderLayout());
        
        //add food information
        foodID = new JLabel(orderlist.getFood().getFoodID(),SwingConstants.CENTER);
        foodID.setFont(font1);
        foodName = new JLabel(orderlist.getFood().getName(),SwingConstants.CENTER);
        foodName.setFont(font1);
        foodDescription = new JTextArea("\""+orderlist.getFood().getDescription()+"\"");
        foodDescription.setFont(font2);
        foodDescription.setEditable(false);
        foodDescription.setLineWrap(true);
        foodDescription.setWrapStyleWord(true);
        foodDescription.setColumns(10);
        foodDescription.setRows(3);
        
        JPanel foodInformation = new JPanel(new GridBagLayout());
        
        c.gridx = 0;
        c.gridy = 0;
        foodInformation.add(foodID,c);
        
        c.gridx = 0;
        c.gridy = 1;
        foodInformation.add(foodName,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.fill =GridBagConstraints.HORIZONTAL;
        foodInformation.add(foodDescription,c);
        
        //create Panel for quantity input and output
        JPanel qtyField = new JPanel(new GridLayout(1,3));
        qtyField.add(minusButton);
        qtyField.add(quantityArea);
        quantityArea.setEditable(false);
        quantityArea.setText(String.format("%d",orderlist.getQuantity()));
        qtyField.add(addButton);
        
        //addButton listener for addButton and minusButton
        addButton.addActionListener(new AddButtonListener());
        minusButton.addActionListener(new MinusButtonListener());
        
        JPanel qtyLabel = new JPanel(new FlowLayout(FlowLayout.LEADING,20,10));
        quantityLabel= new JLabel("Quantity : ");
        quantityLabel.setFont(font3);
        qtyLabel.add(quantityLabel);
        qtyLabel.add(qtyField);
        c.gridx = 0;
        c.gridy = 3;
        foodInformation.add(qtyLabel,c);
        
        c.gridx = 0;
        c.gridy = 4;
        remarkLabel.setFont(font3);
        foodInformation.add(remarkLabel,c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 3;
        c.weighty = 0.3;
        c.fill = GridBagConstraints.BOTH;
        remarkArea.setText(orderlist.getRemark());
        foodInformation.add(remarkArea,c);
        
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,10  ));
        buttons.add(comfirmButton);
        buttons.add(cancelButton);
        comfirmButton.addActionListener(new ComfirmButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        
        add(foodInformation,BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
       
        
    }
    
    private class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            int quantity = Integer.parseInt(quantityArea.getText());
            System.out.println(quantity);
            quantityArea.setText(String.format("%d", quantity + 1));
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Number Format Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class MinusButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            int quantity = Integer.parseInt(quantityArea.getText());
            if(quantity > 1)
                quantityArea.setText(String.format("%d", quantity - 1));
        }
    }
    
    private class ComfirmButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            orderlist.setQuantity(Integer.parseInt(quantityArea.getText()));
            orderlist.setRemark(remarkArea.getText());
            orderListControl.updateOrderList(orderID, orderlist);
            dispose();
        }    
    }
     
     private class CancelButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
     }
    
}
