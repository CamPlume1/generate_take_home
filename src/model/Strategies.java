package model;

public enum Strategies {

    BASIC ("Basic Interpretation"),
    ZERO_ENCRYPT("Any data block containing a 0 is not encrypted"),
    REPEAT_FULL_BLOCK("Raw values of data blocks are repeated as new nodes, instead of already processed Strings"),
    REPEAT_RAW_DATA("Raw values of data blocks are repeated instead of processed versions");

   // POST_PROCESS("Special characters are filtered and executed after the body of digits");

    private final String stringValue;

    Strategies(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString(){
        return stringValue;
    }

}
