package pl.tkozyra.expensetrackerapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.params.TransactionParams;
import pl.tkozyra.expensetrackerapi.service.TransactionService;
import pl.tkozyra.expensetrackerapi.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping
    public List<TransactionDto> findAll(@RequestParam(required = false) String type,
                                        @RequestParam(required = false) String currency,
                                        @RequestParam(required = false) Long userId) {
        return transactionService.findAll(new TransactionParams(type, currency, userId, userService));
    }

    @GetMapping("/{id}")
    public TransactionDto findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.save(transactionDto);
    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.save(transactionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
