

package ui;

import control.OrderControl;
import control.OrderListControl;
import control.TableListControl;
import domain.Member;
import domain.Order;
import domain.Orderlist;
import domain.Staff;
import domain.Tables;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TableLayout extends JFrame{
    
    private ArrayList<JButton> tablesButton;
    private ArrayList<Tables> tables ;
    private OrderControl orderControl;
    private TableListControl tablesControl;
    private OrderListControl orderListControl;
    private Order order;
    
    private JRadioButton occupied = new JRadioButton("Occupy", true);
    private JRadioButton takeOrder = new JRadioButton("Take Order");
    private JRadioButton makePayment = new JRadioButton("Make Payment");
    private ButtonGroup buttonGroup = new ButtonGroup();

    private JLabel tableAvai = new JLabel("Available: ");
    private JLabel tableOcupy = new JLabel("Occupied: ");
    private JLabel tableOrdered = new JLabel("Ordered: ");
    Payment PaymentWindow;
    Staff staff = null;
    
    private String[] statusValue = {"Available","Occupied","Ordered"};
    int index = 0;
    
    
    public TableLayout(Staff staff){
        setLayout(new BorderLayout());
        tablesButton = new ArrayList<JButton>();
        orderControl = new OrderControl();
        tablesControl = new TableListControl();
        orderListControl = new OrderListControl();
        this.staff =staff;
        
        tables = tablesControl.getRecord();
        
        JPanel tablesMap = new JPanel(new GridLayout(tables.size()/5 ,5, 50,50));
        
        JButton temp = null;
        for(int i=0 ; i<tables.size() ; i++){
            temp = new JButton(String.format("%d", tables.get(i).getTableNo()));
            temp.setActionCommand(String.format("%d", i));
            temp.setPreferredSize(new Dimension(180, 70));
            temp.addActionListener(new tablesButtonListener());
            
            tablesButton.add(temp);
            tablesMap.add(temp);
        }
        JScrollPane tableScroll = new JScrollPane(tablesMap);
        tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScroll.getVerticalScrollBar().setUnitIncrement(16);
        
        buttonGroup.add(occupied);
        buttonGroup.add(takeOrder);
        buttonGroup.add(makePayment);
        
        countTableStatus();
        checkTableStatus();
        
        JPanel controlPanel = new JPanel(new GridLayout(1,2));
        JPanel tableInformation = new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
        JPanel radioButton = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,20));
        
        tableInformation.add(tableAvai);
        tableInformation.add(tableOcupy);
        tableInformation.add(tableOrdered);
        
        radioButton.add(occupied);
        radioButton.add(takeOrder);
        radioButton.add(makePayment);
        
        controlPanel.add(tableInformation);
        controlPanel.add(radioButton);
        
        add(controlPanel,BorderLayout.SOUTH);
        add(tableScroll, BorderLayout.CENTER);
        
        
      
        
        
        
    }
    
    private class tablesButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            index = Integer.parseInt(e.getActionCommand());
            
           if(occupied.isSelected()){
                if(tables.get(index).getStatus().equalsIgnoreCase(statusValue[0])){
                    
                    String newID =orderControl.findNewID();
                    order =new Order();
                    order.setOrderID(newID);
                    order.setTable(tables.get(index));
                    order.setStaff(staff);
                    
                    orderControl.createOrder(order);
                    
                    tables.get(index).setStatus(statusValue[1]);
                    tables.get(index).setHoldingOrder(newID);
                    tablesControl.updateStatus(tables.get(index));
                    
                }else if(tables.get(index).getStatus().equalsIgnoreCase(statusValue[1])){
                    String ID = tables.get(index).getHoldingOrder();
                    order = orderControl.getRecord(ID);
                    orderControl.deleteOrder(order);
                    
                    tables.get(index).setStatus(statusValue[0]);
                    tables.get(index).setHoldingOrder(null);
                    tablesControl.updateStatus(tables.get(index));
                }
                
            }else if(takeOrder.isSelected()){
                
                if(tables.get(index).getStatus().equalsIgnoreCase(statusValue[1])||tables.get(index).getStatus().equalsIgnoreCase(statusValue[2])){
                    order = orderControl.getRecord(tables.get(index).getHoldingOrder());
                    OrderMenuFrame orderMenu = new OrderMenuFrame(order);
                    orderMenu.addWindowListener(new mainWindowListener());
                    orderMenu.setTitle("Order Menu");
                    orderMenu.setSize(1270, 720);
                    orderMenu.setLocationRelativeTo(null);
                    orderMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    orderMenu.setVisible(true);
                    
                    
                }
            }else if(makePayment.isSelected()){
                if(tables.get(index).getStatus().equalsIgnoreCase(statusValue[2])){
                    order = orderControl.getRecord(tables.get(index).getHoldingOrder());
                    PaymentWindow = new Payment(order,tables.get(index));
                    PaymentWindow.setTitle("Payment");
                    PaymentWindow.setSize(1270,720);
                    PaymentWindow.setResizable(true); 
                    PaymentWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    PaymentWindow.setLocationRelativeTo(null);
                    PaymentWindow.setVisible(true);
                    PaymentWindow.addWindowListener(new paymentWindowListener());
                    
                }
            }
           
           checkTableStatus();
           countTableStatus();
        }
        
    }
    private class paymentWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
           
            checkTableStatus();
            countTableStatus();
            
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
        
        
    }
    
    private class mainWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
            ArrayList<Orderlist> orderList = orderListControl.getRecord(tables.get(index).getHoldingOrder());
            if(!orderList.isEmpty()){
                tables.get(index).setStatus(statusValue[2]);
                tablesControl.updateStatus(tables.get(index));
            }else{
                tables.get(index).setStatus(statusValue[1]);
                tablesControl.updateStatus(tables.get(index));
            }
            checkTableStatus();
            countTableStatus();
            
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
        
        
    }
    
    private void checkTableStatus(){
        tables = tablesControl.getRecord();
        
        for(int i=0 ; i<tables.size(); i++){
            if(tables.get(i).getStatus().equalsIgnoreCase(statusValue[0])){
                tablesButton.get(i).setBackground(new Color(0,128,0));
            }else if(tables.get(i).getStatus().equalsIgnoreCase(statusValue[1])){
                tablesButton.get(i).setBackground(Color.yellow);
            }else if(tables.get(i).getStatus().equalsIgnoreCase(statusValue[2])){
                tablesButton.get(i).setBackground(new Color(178,34,34));
            }
        }
        
    }
    
    private void countTableStatus(){
        tables = tablesControl.getRecord();
        
        int avai=0 ,ocpy=0,orded=0;
        
        for(int i=0 ; i< tables.size(); i++){
            switch(tables.get(i).getStatus()){
                case "Available":
                    avai++;
                    break;
                case "Occupied":
                    ocpy++;
                    break;
                case "Ordered":
                    orded++;
                    break;
            }
        }
        
        tableAvai.setText(String.format("Available : %d", avai));
        tableOcupy.setText(String.format("Occupied  : %d", ocpy));
        tableOrdered.setText(String.format("Ordered   : %d", orded));
        
    }
    
    
    
}
