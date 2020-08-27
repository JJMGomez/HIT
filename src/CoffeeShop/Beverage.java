package CoffeeShop;

public abstract class Beverage {
    String description;
    Size size = Size.Grande;

    public abstract double cost();

    public String getDescription(){
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
