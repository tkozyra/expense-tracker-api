package pl.tkozyra.expensetrackerapi.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found.");
    }

}
