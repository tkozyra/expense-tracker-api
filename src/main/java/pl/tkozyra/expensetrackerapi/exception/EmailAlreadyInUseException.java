package pl.tkozyra.expensetrackerapi.exception;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("Email already in use");
    }

}
