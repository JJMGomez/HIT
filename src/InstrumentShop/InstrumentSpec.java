package InstrumentShop;

import InstrumentShop.Enums.InstrumentBrand;
import InstrumentShop.Enums.InstrumentType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


/**
 * SRP原则：InstrumentSpec 用于处理specification
 * */
public class InstrumentSpec {
    private Map<String, Object> properties = new HashMap<>();

    public InstrumentSpec() {

    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public InstrumentType getInstrumentType() {
        return (InstrumentType) get("InstrumentType");
    }
    public InstrumentBrand getInstrumentBrand() {
        return (InstrumentBrand) get("InstrumentBrand");
    }

    public InstrumentSpec setInstrumentType(InstrumentType instrumentType) {
        put(instrumentType);
        return this;
    }

//    public InstrumentClass getInstrumentClass() {
//        return (InstrumentClass) get("InstrumentClass");
//    }
//
    public InstrumentSpec setType(InstrumentType instrumentType) {
        put(instrumentType);
        return this;
    }

    public InstrumentSpec setBrand(InstrumentBrand instrumentBrand){
        put(instrumentBrand);
        return this;
    }



    private String getNameOf(Object property) {
        return property.getClass().getSimpleName();
    }

    Object get(String key) {
        return properties.get(key);
    }

    private void put(Object value) {
        properties.put(getNameOf(value), value);
    }

    boolean matches(InstrumentSpec otherSpec) {
        return otherSpec.properties
                .keySet()
                .stream()
                .allMatch(valuesMatch(otherSpec));
    }

    private Predicate<String> valuesMatch(InstrumentSpec otherSpec) {
        return otherKey -> otherSpec.get(otherKey).equals(properties.get(otherKey));
    }
}
