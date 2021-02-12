package pl.tkozyra.expensetrackerapi.controller;

import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.exception.TransactionNotFoundException;
import pl.tkozyra.expensetrackerapi.mapper.TransactionMapper;
import pl.tkozyra.expensetrackerapi.service.TransactionService;
import pl.tkozyra.expensetrackerapi.utils.StringToTransactionTypeConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final StringToTransactionTypeConverter stringToTransactionTypeConverter;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public List<TransactionDto> findAll() {
        return transactionService
                .findAll()
                .stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Long id) {
        return transactionMapper.mapToDto(transactionService
                .findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id)));
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.mapToEntity(transactionDto);
        Transaction createdTransaction = transactionService.create(transaction);
        return transactionMapper.mapToDto(createdTransaction);

    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionService
                .findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setTransactionType(stringToTransactionTypeConverter.convert(transactionDto.getType()));
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        Transaction updatedTransaction = transactionService.save(transaction);
        return transactionMapper.mapToDto(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    public TransactionController(TransactionService transactionService,
                                 StringToTransactionTypeConverter stringToTransactionTypeConverter,
                                 TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.stringToTransactionTypeConverter = stringToTransactionTypeConverter;
        this.transactionMapper = transactionMapper;
    }
}
