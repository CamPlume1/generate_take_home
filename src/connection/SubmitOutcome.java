package connection;


/**
 * The `SubmitOutcome` enum represents the possible outcomes when submitting an answer.
 * It includes two values: SUCCESS and FAILURE, each associated with a specific message.
 * Instances of this enum are used to convey the result of a submission attempt.
 **/
public enum SubmitOutcome {


    /**
     * Represents the outcome when the submission is successful,
     * indicating that the correct answer has been generated.
     */
    SUCCESS("Success: Correct answer generated"),

    /**
     * Represents the outcome when the submission is unsuccessful,
     * indicating that an incorrect answer has been generated.
     */
    FAILURE("Failure: Incorrect answer generated");

    // The message associated with each outcome
    private  final String val;

    //Constructor
    SubmitOutcome(String s) {
        val = s;
    }

    /**
     * Returns the string representation of the SubmitOutcome.
     *
     * @return The message associated with the outcome.
     */
    @Override
    public String toString(){
        return val;
    }

}
