package pl.tkozyra.expensetrackerapi.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.utils.StringToTransactionTypeConverter;

/**
 * Mapper class responsible for entity<->dto mapping for transactions
 */
@Component
public class TransactionMapper {

    private final ModelMapper modelMapper;
    private final StringToTransactionTypeConverter stringToTransactionTypeConverter;

    public TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        transactionDto.setUserId(transaction.getUser().getId());
        return transactionDto;
    }

    public Transaction mapToEntity(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setTransactionType(stringToTransactionTypeConverter.convert(transactionDto.getType()));
        return transaction;
    }

    public TransactionMapper(ModelMapper modelMapper,
                             StringToTransactionTypeConverter stringToTransactionTypeConverter) {
        this.modelMapper = modelMapper;
        this.stringToTransactionTypeConverter = stringToTransactionTypeConverter;
    }
}
