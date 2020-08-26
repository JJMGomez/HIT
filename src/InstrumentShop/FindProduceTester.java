package InstrumentShop;

import InstrumentShop.Enums.InstrumentBrand;
import InstrumentShop.Enums.InstrumentType;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

public class FindProduceTester {
    public static void main(String[] args){

        initializeInventory();

        InstrumentSpec instrumentSpec = new InstrumentSpec()
                .setType(InstrumentType.SERVER);

        Inventory.searchFor(instrumentSpec);
    }

    private static Inventory initializeInventory() {
        Inventory inventory = new Inventory();

        InstrumentSpec sampleDesktop = getSampleDesktop();
        inventory.addInstrument("11277", 3999.95, sampleDesktop);

        InstrumentSpec sampleLaptop = getSamplelaptop();
        inventory.addInstrument("V95693", 1499.95, sampleLaptop);

        InstrumentSpec sampleServer = getSampleServer();
        inventory.addInstrument("70108276", 2295.95, sampleServer);

        return inventory;
    }

    private static InstrumentSpec getSampleDesktop() {
        return new InstrumentSpec()
                .setType(InstrumentType.DESKTOP)
                .setBrand(InstrumentBrand.APPLE);
    }

    private static InstrumentSpec getSamplelaptop() {
        return new InstrumentSpec()
                .setType(InstrumentType.LAPTOP)
                .setBrand(InstrumentBrand.THINKPAD);
    }

    private static InstrumentSpec getSampleServer() {
        return new InstrumentSpec()
                .setType(InstrumentType.SERVER)
                .setBrand(InstrumentBrand.DELL);
    }



}
