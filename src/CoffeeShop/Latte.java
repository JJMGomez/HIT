package CoffeeShop;

public class Latte extends Beverage {
    public Latte(){
        description = "拿铁";
    }

    @Override
    public double cost() {
        switch (getSize()){
            case Tall:
                return 31;
            case Grande:
                return 33;
            case Venti:
                return 35;
            default:
                return 33;
        }
    }
}
