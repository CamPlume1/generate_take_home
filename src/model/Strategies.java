package model;

public enum Strategies {

    BASIC ("Basic Interpretation"),
    ZERO_ENCRYPT("Any data block containing a 0 is not encrypted"),
    POST_PROCESS("Special characters are filtered and executed after the body of digits");

    private final String stringValue;

    Strategies(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString(){
        return stringValue;
    }

}
