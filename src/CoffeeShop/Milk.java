package CoffeeShop;

public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return this.beverage.getDescription() + " 加 牛奶";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 3;
    }
}
