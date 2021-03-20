package pl.tkozyra.expensetrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity<Object> handleTransactionNotFound(WebRequest request) {

        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "Transaction not found",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(WebRequest request) {

        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "User not found",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    protected ResponseEntity<Object> handleEmailAlreadyInUse(WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                "Given email address is already in use",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    protected ResponseEntity<Object> handleUsernameAlreadyInUse(WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                "Given username is already in use",
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
