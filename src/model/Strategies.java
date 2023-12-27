package model;

/**
 * Enums representing the different strategies supported by this program.
 * S
 */
public enum Strategies {

    /**
     * Basic Interpretation
     */
    BASIC ("Basic Interpretation"),

    /**
     * Any data block containing a 0 is not encrypted
     */
    ZERO_ENCRYPT("Any data block containing a 0 is not encrypted"),

    /**
     * Raw values of data blocks are repeated as new nodes, instead of already processed Strings
     */
    REPEAT_FULL_BLOCK("Raw values of data blocks are repeated as new nodes, instead of already processed Strings"),

    /**
     * Special characters are filtered and executed after the body of digits
     */
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
