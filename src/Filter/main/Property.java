package Filter.main;

public enum Property {
    COMPANYNAME, NAME, TITLE, COUNTRY;

    public String toString() {
        switch(this) {
            case COMPANYNAME: return "companyName";
            case NAME: return "name";
            case TITLE: return "title";
            case COUNTRY: return "country";
            default:    return "unknown";
        }
    }
}
