
package domain;

public class Menu {
    private String foodID;
    private String name;
    private String description;
    private double price;
    private char categorise;

    public Menu() {
    }

    public Menu(String foodID, String name, String description, double price, char categorise) {
        this.foodID = foodID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categorise = categorise;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getCategorise() {
        return categorise;
    }

    public void setCategorise(char categorise) {
        this.categorise = categorise;
    }

    @Override
    public String toString() {
        return "Menu{" + "foodID=" + foodID + ", name=" + name + ", description=" + description + ", price=" + price + ", categorise=" + categorise + '}';
    }
   
    
    
}
