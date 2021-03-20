package pl.tkozyra.expensetrackerapi.dto.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.utils.TransactionType;

/**
 * Mapper class responsible for entity<->dto mapping for transactions
 */
@AllArgsConstructor
@Component
public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        transactionDto.setUserId(transaction.getUser().getId());
        return transactionDto;
    }

    public Transaction mapToEntity(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setTransactionType(TransactionType.valueOf(transactionDto.getType().toUpperCase()));
        return transaction;
    }

}
