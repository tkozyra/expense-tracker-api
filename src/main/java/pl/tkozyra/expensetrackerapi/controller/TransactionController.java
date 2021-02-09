package pl.tkozyra.expensetrackerapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.entity.Transaction;
import pl.tkozyra.expensetrackerapi.service.TransactionService;
import pl.tkozyra.expensetrackerapi.utils.StringToTransactionTypeConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final ModelMapper modelMapper;
    private final StringToTransactionTypeConverter stringToTransactionTypeConverter;

    @GetMapping
    public List<TransactionDto> findAll() {
        return transactionService
                .findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Long id) {
        return this.convertEntityToDto(transactionService
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction with given ID does not exist.")));
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = convertDtoToEntity(transactionDto);
        Transaction createdTransaction = transactionService.create(transaction);
        return convertEntityToDto(createdTransaction);

    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionService
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction with given ID does not exist."));

        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setTransactionType(stringToTransactionTypeConverter.convert(transactionDto.getType()));
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        Transaction updatedTransaction = transactionService.save(transaction);
        return this.convertEntityToDto(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    public TransactionController(TransactionService transactionService,
                                 ModelMapper modelMapper,
                                 StringToTransactionTypeConverter stringToTransactionTypeConverter) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
        this.stringToTransactionTypeConverter = stringToTransactionTypeConverter;
    }


    //entity <-> dto conversion

    private TransactionDto convertEntityToDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        return transactionDto;
    }

    private Transaction convertDtoToEntity(TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setTransactionType(stringToTransactionTypeConverter.convert(transactionDto.getType()));
        return transaction;
    }

}
