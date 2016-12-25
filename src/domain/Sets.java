
package domain;

public class Sets {
    
    private String name;
    private SetMenu[] setMenu;
    private double price;
    private String description;

    public Sets() {
    }

    public Sets(String name, SetMenu[] setMenu, double price, String description) {
        this.name = name;
        this.setMenu = setMenu;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SetMenu[] getSetMenu() {
        return setMenu;
    }

    public void setSetMenu(SetMenu[] setMenu) {
        this.setMenu = setMenu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
