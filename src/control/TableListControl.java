/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.TableListDA;
import domain.Tables;
import java.util.ArrayList;
/**
 *
 * @author khooe
 */
public class TableListControl {
        TableListDA tableListDA;
    
    public TableListControl(){
        tableListDA = new TableListDA();
    }
    
    public Tables getRecord(int tablesNo){
        return tableListDA.getRecord(tablesNo);
    }
    
    public ArrayList<Tables> getRecord(){
        return tableListDA.getRecord();
    }
    
    public void addTable(Tables tables){
        tableListDA.addTable(tables);
    }
    
    public void updateTable(Tables tables){
        tableListDA.updateTable(tables);
    }
    
    public void deleteTable(Tables tables){
        tableListDA.deleteTable(tables);
    }
    
    public void updateStatus(Tables tables){
        tableListDA.updateStatus(tables);
    }
}
