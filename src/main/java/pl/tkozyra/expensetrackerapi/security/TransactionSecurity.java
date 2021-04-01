package pl.tkozyra.expensetrackerapi.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.tkozyra.expensetrackerapi.service.TransactionService;
import pl.tkozyra.expensetrackerapi.service.UserDetailsImpl;

@AllArgsConstructor
@Component("transactionSecurity")
public class TransactionSecurity {

    TransactionService transactionService;

    public boolean isOwnedByUser(Authentication authentication, Long transactionId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long ownerId = transactionService.getTransactionOwnerId(transactionId);
        return userDetails.getId().equals(ownerId);
    }
}
