/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import da.StaffDA;
import domain.Staff;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author khooe
 */
public class LoginScreen extends JFrame{
    
    private StaffDA staffDA;
    private JTextField userField = new JTextField();
    private JPasswordField passField = new JPasswordField();
    private JButton comfirmButton = new JButton("Comfirm");
    private JButton cancelButton = new JButton("Cancel");
    

    public LoginScreen (){
        staffDA = new StaffDA();
        setLayout(new BorderLayout());
        
        JPanel form = new JPanel(new GridLayout(2,2));
        form.add(new JLabel("Username"));
        form.add(userField);
        form.add(new JLabel("Password"));
        form.add(passField);
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(comfirmButton);
        buttons.add(cancelButton);
        comfirmButton.addActionListener(new ComfirmButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        
        add(form,BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);
        
    }
    
    private class ComfirmButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = userField.getText();
            char[] password = passField.getPassword();
            Staff staff = null;
            
            try{
                 staff = staffDA.getRecord(id);
                
                for(int i=0 ; i<staff.getPassword().length();i++){
                    if(staff.getPassword().charAt(i)!= password[i])
                        throw new NullPointerException();
                }
                
                RestourantApplication restourant = new RestourantApplication(staff);
                restourant.setTitle("Kanny Restaurant");
                restourant.addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent e) {
    setVisible(true);
    userField.setText("");
    passField.setText(null);
    
  }
});
                restourant.setSize(1270,720);
                restourant.setLocationRelativeTo(null);
                restourant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                restourant.setVisible(true);
                setVisible(false);
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Wrong Password or Username!");
            }
            
        }
    
    }
    
    
    
    private class CancelButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }
    
    public static void main(String[] args) {
        LoginScreen login = new LoginScreen();
        login.setTitle("login Screen");
        login.setSize(400,200);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
    
}
