/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import da.OrderDA;
import domain.Order;
import static java.awt.image.ImageObserver.HEIGHT;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author haoguang
 */
public class PaymentReport extends javax.swing.JFrame {

    private TableRowSorter<TableModel> sorter;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final DateFormat dateFormat2 = new SimpleDateFormat("MMMM yyyy");
    private DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{
                "Order ID","Table No", "Staff ID", "Member ID", "Payment Type", "Check Out Time", "Card Last 4 Digit"
            }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    ;

    };
    /**
     * Creates new form PaymentReport
     */
    public PaymentReport() {
        
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jMonthChooser1.addPropertyChangeListener(new PropertyListener());
        jYearChooser1.addPropertyChangeListener(new PropertyListener());
        jTable1.setModel(tableModel);
        sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Payment Report for");

        jCheckBox1.setText("Year Only");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addContainerGap(322, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fillTable() {
        tableModel.getDataVector().removeAllElements();
        jTable1.revalidate();
        OrderDA orderDA = new OrderDA();

        Object[] obj = null;
        try{
        Date date = dateFormat.parse("1/"+jMonthChooser1.getMonth()+"/"+jYearChooser1.getYear()+" 0:0:0");
        
        Timestamp choosenMonthYear;
        String Option;
        if (jCheckBox1.isSelected()) {
            
            date = dateFormat.parse("1/1/"+jYearChooser1.getYear()+" 0:0:0");
            choosenMonthYear = new Timestamp(date.getTime());
            Option = OrderDA.YEAR;
        } else {
            date = dateFormat.parse("1/"+(jMonthChooser1.getMonth()+1)+"/"+jYearChooser1.getYear()+" 0:0:0");
            choosenMonthYear = new Timestamp(date.getTime());
            Option = OrderDA.MONTH;
        }
        ArrayList<Order> orderList = orderDA.getRecord(choosenMonthYear, Option);
        if (!orderList.isEmpty()) {
            for (int i = 0; i < orderList.size(); i++) {

                obj = new Object[]{orderList.get(i).getOrderID(), orderList.get(i).getTable().getTableNo(),orderList.get(i).getStaff() == null ? "No Staff":orderList.get(i).getStaff().getStaffID(), 
                    orderList.get(i).getMember() == null ? "No Member":orderList.get(i).getMember().getMemberID(),orderList.get(i).getPaymentType(),dateFormat.format(orderList.get(i).getCheckOutTime()),orderList.get(i).getCardLast4Digit()};
                tableModel.addRow(obj);

            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no payment made in " + dateFormat2.format(choosenMonthYear.getTime()), "Message", JOptionPane.WARNING_MESSAGE);
        }
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    

    private class PropertyListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("month".equals(evt.getPropertyName()) || "year".equals(evt.getPropertyName())) {
                fillTable();
            }

        }
    }

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jMonthChooser1.setEnabled(false);
        } else {
            jMonthChooser1.setEnabled(true);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaymentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaymentReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
