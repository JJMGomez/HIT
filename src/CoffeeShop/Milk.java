package CoffeeShop;

public class Milk extends CondimentDecorator {

    Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 加 牛奶";
    }

    public Size getSize(){
        return this.beverage.getSize();
    }

    @Override
    public double cost() {
        return beverage.cost() + 3;
    }
}
