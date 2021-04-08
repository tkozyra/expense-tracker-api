package pl.tkozyra.expensetrackerapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.tkozyra.expensetrackerapi.dto.TransactionDto;
import pl.tkozyra.expensetrackerapi.params.TransactionParams;
import pl.tkozyra.expensetrackerapi.service.TransactionService;
import pl.tkozyra.expensetrackerapi.service.UserDetailsImpl;
import pl.tkozyra.expensetrackerapi.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransactionDto> findAll(@RequestParam(required = false) String type,
                                        @RequestParam(required = false) String currency,
                                        @RequestParam(required = false) Long userId) {
        return transactionService.findAll(new TransactionParams(type, currency, userId, userService));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<TransactionDto> findUserTransactions(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return transactionService.findByUserId(userDetails.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransactionDto findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.save(transactionDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.save(transactionDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteTransaction(@PathVariable Long id, Authentication authentication) {
        transactionService.delete(id);
    }
}
