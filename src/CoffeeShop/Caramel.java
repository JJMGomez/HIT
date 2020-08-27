package CoffeeShop;

public class Caramel extends CondimentDecorator {
    Beverage beverage;

    public Caramel(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + " 加 焦糖";
    }

    public Size getSize(){
        return this.beverage.getSize();
    }

    @Override
    public double cost() {
        return beverage.cost() + 2;
    }
}
