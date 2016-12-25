/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.MenuDA;
import domain.Menu;
import java.util.ArrayList;

/**
 *
 * @author khooe
 */
public class MenuControl {
    MenuDA menuDA;
    
    public MenuControl(){
        menuDA = new MenuDA();
    }
    
    public ArrayList<Menu> getRecord(){
        return menuDA.getRecord();
    }
    
    public void addRecord(Menu menu){
        menuDA.addRecord(menu);
    }
    
    public void updateItem(Menu menu){
        menuDA.updateItem(menu);
    }
    
    public void deleteItem(Menu menu){
        menuDA.deleteItem(menu);
    }
}
