
package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import domain.Member;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomerDA {

    private String host = "jdbc:derby://localhost:1527/assignment";
    private String tableName = "Member";
    private String user = "assignment";
    private String password = "assignment";
    private Connection conn;
    private PreparedStatement stmt;
    
    public CustomerDA(){
        createConnection();
    }
    
    public ArrayList<Member> getRecord(){
        String queryStr = "SELECT * FROM "+tableName;
        ArrayList<Member> member =new ArrayList<Member>();
        
        try{
            stmt=conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            String date = new String(rs.getDate("registerdate").toString());
            
            GregorianCalendar registerdate = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8)));
            date = new String(rs.getDate("BOD").toString());
            GregorianCalendar BOD = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8)));
            
            member.add(new Member(rs.getString("MemberID"), registerdate,rs.getInt("point"),rs.getString("firstname"),rs.getString("lastname")
                    ,rs.getString("address"),rs.getString("IC"),BOD,rs.getString("email"),rs.getString("phoneNo")));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return member;
    }
    
    public Member getRecord(String customerID){
        String queryStr = "SELECT * FROM "+tableName + " WHERE MEMBERID = ?";
        Member member =null;
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, customerID);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
            String date = new String(rs.getDate("registerdate").toString());
            
            GregorianCalendar registerdate = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8)));
            date = new String(rs.getDate("BOD").toString());
            GregorianCalendar BOD = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8)));
            
            member = new Member(customerID, registerdate,rs.getInt("point"),rs.getString("firstname"),rs.getString("lastname")
                    ,rs.getString("address"),rs.getString("IC"),BOD,rs.getString("email"),rs.getString("phoneNo"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return member;
    }
    
    public void addMember(Member member){
        String queryStr = "INSERT INTO "+tableName +" VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1,member.getMemberID() );
            stmt.setString(2, member.getFirstName());
            stmt.setString(3, member.getLastName());
            stmt.setString(4, member.getAddress());
            stmt.setString(5, member.getIC());
            stmt.setDate(6, java.sql.Date.valueOf(String.format("%d-%02d-%02d",member.getDOB().get(Calendar.YEAR),member.getDOB().get(Calendar.MONTH),member.getDOB().get(Calendar.DATE))));
            stmt.setString(7, member.getEmail());
            stmt.setString(8, member.getPhoneNo());
            stmt.setDate(9, java.sql.Date.valueOf(String.format("%d-%02d-%02d",member.getRegisterDate().get(Calendar.YEAR),member.getRegisterDate().get(Calendar.MONTH),member.getRegisterDate().get(Calendar.DATE))));
            stmt.setInt(10, 0);
            
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateMember(Member member){
        String queryStr = "UPDATE "+tableName+" set firstname = ?, lastname = ? , address = ? , ic = ?, bod = ?, email = ?, phoneno = ?, registerdate = ?, point = ? where MEMBERID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getAddress());
            stmt.setString(4, member.getIC());
            stmt.setDate(5, java.sql.Date.valueOf(String.format("%d-%02d-%02d",member.getDOB().get(Calendar.YEAR),member.getDOB().get(Calendar.MONTH),member.getDOB().get(Calendar.DATE))));
            stmt.setString(6,member.getEmail());
            stmt.setString(7, member.getPhoneNo());
            stmt.setDate(8, java.sql.Date.valueOf(String.format("%d-%02d-%02d",member.getRegisterDate().get(Calendar.YEAR),member.getRegisterDate().get(Calendar.MONTH),member.getRegisterDate().get(Calendar.DATE))));
            stmt.setInt(9, member.getPoint());
            stmt.setString(10, member.getMemberID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteMember(Member member){
        String queryStr = "delete from "+tableName+" where MEMBERID = ?";
        
        try{
            stmt=conn.prepareStatement(queryStr);  
            stmt.setString(1,member.getMemberID());
            stmt.executeUpdate();
            
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String findID(){
        String queryStr = "select MEMBERID from "+tableName+" order by memberid desc";
        String newID = new String ("C0000001");
        
        try{
            stmt = conn.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();
            
            
            if(rs.next()){
                String lastID = rs.getString("MEMBERID");
                
                newID = new String(String.format("C%07d", Integer.parseInt(lastID.substring(1))+1)) ;
            }
            
            
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        return newID;
    }


    public static void main(String[] args){
        /* test code
        CustomerDA cu = new CustomerDA();
        System.out.println(cu.getRecord("C0000001"));
        String ID = cu.findID();
        System.out.println(ID);
        
        GregorianCalendar date = new GregorianCalendar(2015,2,25);
        System.out.println();

        Member customer = new Member(ID, new GregorianCalendar(2015,2,25), 0, "firstName", "lastName", "adsfsadfdssdf", "897896541", new GregorianCalendar(1987,5,23), "sdfsdfsdf", "ddfasdfsd");
        cu.addMember(customer);
        System.out.println(cu.getRecord(ID));
        customer.setFirstName("Tom dskfjdsjsdkjfkdjskajsdk");
        cu.updateMember(customer);
        System.out.println(cu.getRecord(ID));
        cu.deleteMember(customer);
        System.out.println(cu.getRecord(ID));
                */
    }
    
    
    private void createConnection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("Connected");
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null,se.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
}
