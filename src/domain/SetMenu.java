
package domain;

public class SetMenu {
    private Menu food;
    private int foodquantity;

    public SetMenu() {
    }

    public SetMenu(Menu food, int foodquantity) {
        this.food = food;
        this.foodquantity = foodquantity;
    }

    public Menu getFood() {
        return food;
    }

    public void setFood(Menu food) {
        this.food = food;
    }

    public int getFoodquantity() {
        return foodquantity;
    }

    public void setFoodquantity(int foodquantity) {
        this.foodquantity = foodquantity;
    }

    @Override
    public String toString() {
        return "SetMenu{" + "food=" + food + ", foodquantity=" + foodquantity + '}';
    }
    
    
}
