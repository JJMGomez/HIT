package CoffeeShop;

public class Espresso extends Beverage{

    public Espresso(){
        description = "浓缩 ";
        size = Size.Grande;
    }
    @Override
    public double cost() {
        switch (getSize()){
            case Tall:
                return 30;
            case Grande:
                return 32;
            case Venti:
                return 34;
            default:
                return 32;

        }
    }
}
