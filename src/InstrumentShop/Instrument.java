package InstrumentShop;


/**
 * SRP原则：持有InstrumentSpec对象，不直接管理具体的specification
 * */
public class Instrument {
    private String serialNumber;
    private double price;
    private InstrumentSpec spec;

    public Instrument(String serialNumber, double price, InstrumentSpec spec){
        this.serialNumber = serialNumber;
        this.price = price;
        this.spec = spec;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public InstrumentSpec getSpec() {
        return spec;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    String getDescription() {
        String instrumentType = getOrDefault(spec.getInstrumentType());
        String instrumentBrand = getOrDefault(spec.getInstrumentBrand());
        return instrumentType + " " +instrumentBrand;
    }

    private String getOrDefault(Enum item) {
        return item == null ? "" : (item.toString() + " ");
    }

    String getPriceInfo() {
        return "  该商品的价格是 " +
                getPrice() + "\n---";
    }

    private String getIntro() {
        return this.getDescription() + "\n" +
                this.getPriceInfo();
    }

    public void printIntro() {
        System.out.println(getIntro());
    }
}
