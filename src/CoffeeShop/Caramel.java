package CoffeeShop;

public class Caramel extends CondimentDecorator {
    public Caramel(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return this.beverage.getDescription() + " 加 焦糖";
    }

    @Override
    public double cost() {
        return this.beverage.cost() + 2;
    }
}
