
package ui;

import control.jobControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import domain.*;

public class RestourantApplication extends JFrame{
    
     private Staff staff;
     private jobControl jobCon ;
        ImageIcon orderIcon = new ImageIcon(getClass().getResource("/images/Order_form.png"));
        ImageIcon custoIcon = new ImageIcon(getClass().getResource("/images/staff.png"));
        ImageIcon staffIcon = new ImageIcon(getClass().getResource("/images/woman.png"));
        ImageIcon foodIcon = new ImageIcon(getClass().getResource("/images/Ecommerce-Food-icon.png"));
        ImageIcon tableIcon = new ImageIcon(getClass().getResource("/images/table.png"));
        
        JButton orderButton = new JButton("Take Order" , orderIcon);
        JButton custoButton = new JButton("Customer", custoIcon);
        JButton staffButton = new JButton("Staff", staffIcon);
        JButton foodButton = new JButton("Food", foodIcon);
        JButton tableButton = new JButton("Table", tableIcon);
        
        
        
        JFrame subFrame = null;
        
        
    public RestourantApplication(Staff staff){
        this.staff = staff;
        
        setLayout(new GridLayout(1,5));
        
        orderButton.setToolTipText("Take Order");
        custoButton.setToolTipText("Maintain Customers Info");
        staffButton.setToolTipText("Maintain Staff Info");
        foodButton.setToolTipText("Maintain Food Info");
        tableButton.setToolTipText("Maintain Table Info");
        
        add(orderButton);
        add(custoButton);
        add(staffButton);
        add(foodButton);
        add(tableButton);
        
        staffButton.setEnabled(false);
        foodButton.setEnabled(false);
        tableButton.setEnabled(false);
        
        if(staff.getJob().getAuthority()){
            staffButton.setEnabled(true);
            foodButton.setEnabled(true);
            tableButton.setEnabled(true );
        }
        
        orderButton.addActionListener(new orderButtonListener());
        custoButton.addActionListener(new custoButtonListener());
        staffButton.addActionListener(new staffButtonListener());
        foodButton.addActionListener(new foodButtonListener());
        tableButton.addActionListener(new tableButtonListener());
        
        
        
    }
    
    private class orderButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           subFrame = new TableLayout(staff);
           subFrame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    
  }
});
           subFrame.setTitle("Kanny Restaurant");
                subFrame.setSize(1270,720);
                subFrame.setLocationRelativeTo(null);
                subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                subFrame.setVisible(true);
                setVisible(false);
        }
    
}
    private class custoButtonListener implements ActionListener{

        //@Override
        public void actionPerformed(ActionEvent e) {
          MemberFrame subFrame = new MemberFrame();
           subFrame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    
  }
});
           subFrame.setTitle("Kanny Restaurant");
                subFrame.setSize(1270,720);
                subFrame.setLocationRelativeTo(null);
                subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                subFrame.setVisible(true);
                setVisible(false);
           
        }
    
}
    private class staffButtonListener implements ActionListener{

        //@Override
        public void actionPerformed(ActionEvent e) {
           StaffFrame subFrame = new StaffFrame();
           subFrame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    
  }
});
           subFrame.setTitle("Kanny Restaurant");
                subFrame.setSize(1270,720);
                subFrame.setLocationRelativeTo(null);
               subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                subFrame.setVisible(true);
                setVisible(false);
        }
    
}
    private class foodButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           subFrame = new FoodTable();
           subFrame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    
  }
});
           subFrame.setTitle("Kanny Restaurant");
               subFrame.setSize(1270,720);
                subFrame.setLocationRelativeTo(null);
                subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                subFrame.setVisible(true);
                setVisible(false);
        }
    
}
    private class tableButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           subFrame = new TableList();
           subFrame.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    
  }
});
           subFrame.setTitle("Kanny Restaurant");
                subFrame.setSize(1270,720);
                subFrame.setLocationRelativeTo(null);
                subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                subFrame.setVisible(true);
                setVisible(false);
        }
    
}
    

    
    public static void main(String[] args) {
       
    }
    
}
