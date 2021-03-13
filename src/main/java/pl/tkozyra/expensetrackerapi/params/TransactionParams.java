package pl.tkozyra.expensetrackerapi.params;

import org.springframework.data.domain.Example;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.service.UserService;
import pl.tkozyra.expensetrackerapi.utils.StringToTransactionTypeConverter;

/**
 * Class representing search params for Transaction, allowing to get filtered results from the database
 */
public class TransactionParams {

    private final String type;
    private final String currency;
    private final Long userId;

    private final UserService userService;
    private final StringToTransactionTypeConverter stringToTransactionTypeConverter;

    public TransactionParams(String type,
                             String currency,
                             Long userId,
                             UserService userService,
                             StringToTransactionTypeConverter stringToTransactionTypeConverter) {
        this.type = type;
        this.currency = currency;
        this.userId = userId;
        this.userService = userService;
        this.stringToTransactionTypeConverter = stringToTransactionTypeConverter;
    }

    public Example<Transaction> convertToExample() {
        Transaction transaction = new Transaction();

        if (this.type != null) {
            transaction.setTransactionType(stringToTransactionTypeConverter.convert(this.type));
        }
        if (this.currency != null) {
            transaction.setCurrency(this.currency);
        }
        if (this.userId != null) {
            transaction.setUser(userService.findById(this.userId));
        }

        return Example.of(transaction);
    }

}
