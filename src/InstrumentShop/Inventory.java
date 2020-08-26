package InstrumentShop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SRP原则：Inventory 用于管理库存
 * */

public class Inventory {
    private static List<Instrument> inventory;

    public Inventory(){
        inventory = new ArrayList<>();
    }

    public void addInstrument(String serialNumber, double price, InstrumentSpec spec){
         Instrument instrument = new Instrument(serialNumber,price,spec);
         inventory.add(instrument);
    }

    /**
     * SRP原则：根据InstrumentSpec查库存，不需要管具体的specificationc
     * */
    private static List<Instrument> search(InstrumentSpec searchSpec){
        return inventory.stream()
                .filter(instrument -> instrument.getSpec().matches(searchSpec))
                .collect(Collectors.toList());
    }

    public static void searchFor(InstrumentSpec inputSpec){
        List<Instrument> matchingInstruments = search(inputSpec);

        if (matchingInstruments.isEmpty()){
            System.out.println("抱歉，没有找到您想要的设备。");
            return;
        }

        System.out.println("您可能对以下商品感兴趣：");
        matchingInstruments.forEach(Instrument::printIntro);
    }
}
