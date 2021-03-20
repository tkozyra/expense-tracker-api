package pl.tkozyra.expensetrackerapi.params;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.service.UserService;
import pl.tkozyra.expensetrackerapi.utils.TransactionType;

/**
 * Class representing search params for Transaction, allowing to get filtered results from the database
 */
@AllArgsConstructor
public class TransactionParams {

    private final String type;
    private final String currency;
    private final Long userId;
    private UserService userService;

    public Example<Transaction> convertToExample() {
        Transaction transaction = new Transaction();

        if (type != null) {
            transaction.setTransactionType(TransactionType.valueOf(type.toUpperCase()));
        }
        if (currency != null) {
            transaction.setCurrency(currency);
        }
        if (userId != null) {
            transaction.setUser(userService.findById(userId));
        }

        return Example.of(transaction);
    }

}
