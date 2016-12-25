/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.CustomerDA;
import domain.Member;
import java.util.ArrayList;


/**
 *
 * @author Guang
 */
public class CustomerControl {
    
    CustomerDA cu;
    public CustomerControl(){
         cu = new CustomerDA();
    }
    
    public ArrayList<Member> getRecord(){
        return cu.getRecord();
    }
    
    public Member getRecord(String customerID){
        return cu.getRecord(customerID);
    }
    
    public void addRecord(Member member){
        cu.addMember(member);
    }
    
    public void updateItem(Member member){
        cu.updateMember(member);
    }
    
    public void deleteItem(Member member){
        cu.deleteMember(member);
    }
    
    public String findNewID(){
        return cu.findID();
    }
}
