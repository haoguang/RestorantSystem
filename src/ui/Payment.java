package ui;

import domain.Member;
import Calculation.RoundOff;
import control.CustomerControl;
import control.OrderControl;
import control.TableListControl;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*; 
import java.io.*;
import domain.Order;
import domain.Staff;
import domain.Tables;
import java.awt.image.BufferedImage;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Calendar;
import javax.imageio.ImageIO;


public class Payment extends JFrame{
    
   
    private JTextField jtfhc =new JTextField(10);
    private JTextField jtfcc =new JTextField(10);
    private JTextField jtforderid =new JTextField(10);
    private JTextField jtfamount =new JTextField(10);
    private JTextField jtfgst =new JTextField(10);
    private JTextField jtfchance =new JTextField(10);
    private JTextField jtfcc1=new JTextField(4);
    private JTextField jtfcc2=new JTextField(4);
    private JTextField jtfcc3=new JTextField(4);
    private JTextField jtfcc4=new JTextField(4);
    
    private javax.swing.JTable jTable1;
    private Object[] columnName = {"Food Name", "Quantity", "price"};
    private JButton jbtpayment = new JButton("Payment");
    private JButton jbtcancel = new JButton("Cancel");
    
    private JButton jbtCash1 = new JButton("100");
    private JButton jbtCash2 = new JButton("200");
    private JButton jbtCash3 = new JButton("500");
    private JButton jbtCash4 = new JButton("1000");
    
    private JRadioButton jrbhc =new JRadioButton("Hand Cash",true);
    private JRadioButton jrbcc =new JRadioButton("Credit Card");
    private ButtonGroup jbb =new ButtonGroup();
    private JPanel panel10 =new JPanel();
    private JPanel panel11=new JPanel();
    private boolean condition = false;
    private Tables tables = null;
    
    GridBagConstraints gbc =new GridBagConstraints();
    
    
    
    private int GST;
    Order order;
    RoundOff Calculate;
    
    public Payment(Order order,Tables tables){
        this.order = order;
        this.tables = tables;
        
        
        JPanel panel1 = new JPanel(new BorderLayout(30,20));
        JPanel panel3 =new JPanel(new GridLayout(4,1));
        JLabel label1 = new JLabel("Payment");
        JLabel label2 = new JLabel("Payment Type");
        JLabel label4 =new JLabel("Order  Id ");
        JLabel label3 =new JLabel("GST ");
        JLabel label5 =new JLabel("Credit Card  ");
        JLabel label6=new JLabel("Payment  ");
        JLabel label7=new JLabel("Change  ");
        JPanel panel11=new JPanel();
        setLayout(new GridBagLayout());
        jTable1 = new javax.swing.JTable();
        
        panel3.add(label4);
        panel3.add(jtforderid);
        jtforderid.setText(order.getOrderID());
        panel3.add(label3); 
        panel3.add(jtfgst);
        jtfgst.setText(String.format("%f",Order.calculateGST(Order.calculatePayment(order)*0.6)));
        panel3.add(label6);  
        panel3.add(jtfamount);
        jtfamount.setText(String.format("%f",Order.calculatePayment(order)));
        panel3.add(label7);
        panel3.add(jtfchance);
        jtforderid.setEditable(false);
        jtfgst.setEditable(false);
        jtfamount.setEditable(false);
        jtfchance.setEditable(false);
        
        setDataToTable();
        
        panel11.add(panel3,BorderLayout.SOUTH);
        panel11.add(panel10,BorderLayout.CENTER);
        panel1.add(panel11,BorderLayout.WEST);

        JPanel panel2 =new JPanel(new FlowLayout());
        panel2.add(jbtpayment);
        panel2.add(jbtcancel);
        panel1.add(panel2,BorderLayout.SOUTH);
        
        JPanel panel7=new JPanel(new BorderLayout(0,0));
        JPanel panel4 =new JPanel(new BorderLayout(0,0));
        JPanel panel5 =new JPanel(new BorderLayout(0,0));
        JPanel panel8 =new JPanel(new GridLayout(4,1));
        JPanel panel9 =new JPanel(new FlowLayout());
        
        jbb.add(jrbhc);
        jbb.add(jrbcc);
        
        panel4.add(jrbhc,BorderLayout.NORTH);
        panel8.add(label1);
        panel8.add(jtfhc);
        panel8.add(jbtCash1);
        panel8.add(jbtCash2);
        panel8.add(jbtCash3);
        panel8.add(jbtCash4);         
        
        panel5.add(jrbcc,BorderLayout.NORTH);
        panel9.add(jtfcc1);
        panel9.add(jtfcc2);
        panel9.add(jtfcc3);
        panel9.add(jtfcc4);
        jtfcc4.getText();
        
        panel7.add(panel4,BorderLayout.NORTH);
        panel7.add(panel5,BorderLayout.CENTER);
        panel4.add(panel8,BorderLayout.CENTER);
        panel5.add(panel9,BorderLayout.CENTER);
        panel1.add(panel7,BorderLayout.EAST);
        add(panel1);
        
        
        
        panel8.setVisible(true);
        panel9.setVisible(false);
        jbtpayment.addActionListener(new paymentListener());
        jbtcancel.addActionListener(new cancelListener());
        jbtCash1.addActionListener(new cashListener());
        jbtCash2.addActionListener(new cash1Listener());
        jbtCash3.addActionListener(new cash2Listener());
        jbtCash4.addActionListener(new cash3Listener());
        jtfhc.getDocument().addDocumentListener(new CalculateDocumentListener());
        jrbhc.addActionListener(new ActionListener(){
           @Override
        public void actionPerformed(ActionEvent e) {
          if(jrbhc !=null){
            panel8.setVisible(true);
            panel9.setVisible(false);
          }else
              panel8.setVisible(false);
          
        }
        });
        jrbcc.addActionListener(new ActionListener(){
           @Override
        public void actionPerformed(ActionEvent e) {
          if(jrbcc !=null){
            panel9.setVisible(true);
            panel8.setVisible(false);
          }else
              panel9.setVisible(false);
          
        }
        });
        
        
        
    }

   class cashListener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
          
           jtfhc.setText("100");
       }
       }
   
   class cash1Listener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
          
           jtfhc.setText("200");
       }
      }
   class cash2Listener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
          
           jtfhc.setText("500");
       }
      }
   class cash3Listener implements ActionListener{
       @Override
       public void actionPerformed(ActionEvent e){
          
           jtfhc.setText("1000");
       }
      }
    
    class paymentListener implements ActionListener{
     @Override
        public void actionPerformed(ActionEvent e) {
           if(Double.parseDouble(jtfchance.getText()) >= 0){
                CustomerControl memberControl = new CustomerControl();
                Member member = new Member();
                Calendar calendar = Calendar.getInstance();
                
                if(jtfcc4.getText().length() > 0)
                    order.setCardLast4Digit(Integer.parseInt(jtfcc4.getText()));
                
                order.setCheckOutTime(new java.sql.Timestamp(calendar.getTime().getTime()));
                if(jrbhc.isSelected())
                    order.setPaymentType("Cash");
                else
                    order.setPaymentType("CreditCard");
                
                OrderControl orderControl = new OrderControl();
                orderControl.updateOrder(order);
                if(order.getMember() != null){
                member = memberControl.getRecord(order.getMember().getMemberID());
                member.setPoint(member.getPoint()+ Order.calculatePoints((int)Order.calculatePayment(order)));
                
                memberControl.updateItem(member);
                }
                
                tables.setStatus("Available");
                tables.setHoldingOrder(null);
                new TableListControl().updateStatus(tables);
                
                condition = true;
                printRecipe();
                
                dispose();
           }
           else{
               JOptionPane.showMessageDialog(null, "Payment Not Enough", "Invalid Payment", JOptionPane.ERROR_MESSAGE);
           }
           
            
            
        }
    }
    
private void printRecipe(){
         BufferedImage image = new BufferedImage(1300, 800, BufferedImage.TYPE_INT_RGB); 
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setBackground(new Color(255,255,153));
        g.clearRect(0,0,(int)1300,(int)800);
        g.setColor(new Color(30,144,255));
        Font font = new Font("Brush Script Std",1,50);
        Font font1 = new Font("Book Antiqua", 0,20);
        g.setFont(font);
        
        g.drawString("Kanny Restaurant", 100, 80);
        g.setColor(Color.BLACK);
        g.setFont(font1);
        g.drawString("No 88, Jalan Bunga 1/8 Taman Melati", 100, 110);
        g.drawString("53300 , Kuala Lumpur.", 100, 140);
        
        g.drawString("Recipt ID : "+ order.getOrderID(), 100, 210);
        g.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 270);
        g.drawString("Food ID", 100, 300);
        g.drawString("Food Name", 277, 300);
        g.drawString("Quantity" ,812,300);
        g.drawString("Price(RM)",1101 , 300);
        g.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 330);
        
        String Quantity = null;
        String Price = null;
        int row = 0;
        
        
        for(int i=0; i<order.getOrderlist().size() ; i++){
            row = 360 + i*30;
            g.drawString(order.getOrderlist().get(i).getFood().getFoodID() ,111, row);
            g.drawString(order.getOrderlist().get(i).getFood().getName()+ String.format("(RM %.2f)", order.getOrderlist().get(i).getFood().getPrice()) , 277, row);
            g.drawString(String.format("%2d",order.getOrderlist().get(i).getQuantity()), 845, row);
            g.drawString(String.format("%7.2f",order.getOrderlist().get(i).getQuantity()*order.getOrderlist().get(i).getFood().getPrice() ), 1123, row);
            
        }
        
        
        double total = Order.calculatePayment(order);
        String totalString = String.format("Total : %8.2f",total);
        double gst = Order.calculateGST(total);
        String totalgst = String.format("GST 6%% : %.2f",gst);
        g.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, row+100);
        g.drawString("Total : ", 1046, row+130);
        g.drawString(String.format("%8.2f",total), 1123, row+130);
        g.drawString("GST 6% : ", 1035, row+160);
        g.drawString(String.format( "%.2f",gst), 1145 , row+160);

    try {  
        ImageIO.write(image, "jpg", new File("C:/Users/haoguang/"+order.getOrderID()+".jpg")); 
    } catch (IOException e) {  
        e.printStackTrace(); 
    }
    
    
    
}
    
    class cancelListener implements ActionListener{
     @Override
        public void actionPerformed(ActionEvent e) {
            
            condition = false;
            dispose();
            
     }    
    }
    private class CalculateDocumentListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            try{
            calculateCharges(Double.parseDouble(jtfhc.getText()));
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Please enter only number.", "NumberFormatException", JOptionPane.ERROR_MESSAGE);
            }
            
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            try{
                calculateCharges(Double.parseDouble(jtfhc.getText()));
            }catch (NumberFormatException ex){
                calculateCharges(0.0);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculateCharges(Double.parseDouble(jtfhc.getText()));
        }

    }
    
    private void calculateCharges(Double moneyReceive){
       double charge = moneyReceive - Order.calculatePayment(order);
        
        jtfchance.setText(String.format("%.2f", charge));
        //settext to the text field you wanted.
    }
    private void setDataToTable(){
       
        
        panel10.removeAll();
        panel10.updateUI();
        panel10.repaint();
        
        Object [][]  tableElements= new Object[order.getOrderlist().size()][columnName.length];
        
        for(int i=0 ; i<order.getOrderlist().size() ; i ++){
            for(int j=0 ; j<columnName.length ; j++){
                switch(j){
                    case 0:
                        tableElements[i][j]=order.getOrderlist().get(i).getFood().getName();
                        break;
                    case 1:
                        tableElements[i][j]=order.getOrderlist().get(i).getQuantity();
                        break;
                    case 2:
                        tableElements[i][j]=order.getOrderlist().get(i).getFood().getPrice();
                        
                }
                
            }
        }
        
        jTable1 = new JTable(tableElements, columnName) {
            //use to make cell uneditable but selectable for user
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane tableOrder = new JScrollPane(jTable1);
        tableOrder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableOrder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        panel10.add(tableOrder);
        
    }
    

   
    public static void main(String[] args) {
        
    
           
       
    }
    
} 
    


