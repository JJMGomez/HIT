package CoffeeShop;

public class Frappuccino extends Beverage{

    public Frappuccino(){
        description = "星冰乐";
    }
    @Override
    public double cost() {
        switch (getSize()){
            case Tall:
                return 32;
            case Grande:
                return 34;
            case Venti:
                return 36;
            default:
                return 34;
        }
    }
}
