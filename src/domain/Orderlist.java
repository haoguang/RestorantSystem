
package domain;

import javax.swing.JOptionPane;

public class Orderlist {
    private Menu food;
    private Sets course;
    private int quantity;
    private String Remark;

    public Orderlist() {
    }
    
    

    public Orderlist(Sets course, int quantity, String Remark) {
        this.food = null;
        this.course = course;
        this.quantity = quantity;
        this.Remark = Remark;
    }
    
    public Orderlist(Menu food, int quantity, String Remark){
        this.food = food;
        this.course = null;
        this.quantity = quantity;
        this.Remark = Remark;
    }

    public Menu getFood() {
        return food;
    }

    public void setFood(Menu food) {
        if(course == null)
         this.food = food;
        else
            JOptionPane.showMessageDialog(null, "A course is selected, food and course can not be selected at once", "Error", JOptionPane.ERROR_MESSAGE);
            
        
    }

    public Sets getCourse() {
        return course;
    }

    public void setCourse(Sets course) {
        if(food==null)
        this.course = course;
        else
            JOptionPane.showMessageDialog(null, "A food is selected, food and course can not be selected at once", "Error", JOptionPane.ERROR_MESSAGE);
    }

    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    @Override
    public String toString() {
        return "Orderlist{" + "food=" + food + ", course=" + course + ", quantity=" + quantity + ", Remark=" + Remark + '}';
    }

    
    
    
}
