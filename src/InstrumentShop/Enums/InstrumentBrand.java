package InstrumentShop.Enums;

public enum InstrumentBrand {
    APPLE, THINKPAD,DELL;

    public String toString() {
        switch(this) {
            case APPLE: return "苹果";
            case THINKPAD: return "联想";
            case DELL: return "戴尔";
            default:       return "unspecified";
        }
    }
}
