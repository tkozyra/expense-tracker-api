package pl.tkozyra.expensetrackerapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.dto.mapper.TransactionMapper;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.exception.TransactionNotFoundException;
import pl.tkozyra.expensetrackerapi.params.TransactionParams;
import pl.tkozyra.expensetrackerapi.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<TransactionDto> findAll(TransactionParams transactionParams) {
        return transactionRepository
                .findAll(transactionParams.convertToExample())
                .stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TransactionDto findById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        return transactionMapper.mapToDto(transaction);
    }

    public List<TransactionDto> findByUserId(Long userId) {
        return transactionRepository
                .findByUserId(userId)
                .stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Long getTransactionOwnerId(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return transaction.get().getUser().getId();
        }
        return -1L;
    }

    public TransactionDto save(TransactionDto transactionDto) {
        return transactionMapper.mapToDto(transactionRepository.save(transactionMapper.mapToEntity(transactionDto)));
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
