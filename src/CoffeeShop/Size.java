package CoffeeShop;

public enum Size {
    Tall, Grande,Venti;

    public String toString() {
        switch(this) {
            case Tall: return "中杯";
            case Grande: return "大杯";
            case Venti: return "超大杯";
            default:    return "unspecified";
        }
    }
}
