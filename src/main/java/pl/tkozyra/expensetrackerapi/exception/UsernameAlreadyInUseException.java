package pl.tkozyra.expensetrackerapi.exception;

public class UsernameAlreadyInUseException extends RuntimeException {

    public UsernameAlreadyInUseException() {
        super("Username already in use");
    }

}
