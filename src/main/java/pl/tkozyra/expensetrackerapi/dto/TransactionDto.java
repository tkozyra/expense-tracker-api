package pl.tkozyra.expensetrackerapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private String currency;
    private String type;
    private LocalDate date;
    private String description;
    private Long userId;

}
