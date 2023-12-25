package model;

public enum Strategies {

    BASIC ("Basic Interpretation"), ZERO_ENCRYPT("Any data block containing a 0 is not encrypted");

    private final String stringValue;

    Strategies(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString(){
        return stringValue;
    }

}
