package InstrumentShop.Enums;

public enum InstrumentType {
    LAPTOP, DESKTOP,SERVER;

    public String toString() {
        switch(this) {
            case LAPTOP: return "笔记本";
            case DESKTOP: return "台式机";
            case SERVER: return "服务器";
            default:       return "unspecified";
        }
    }
}
