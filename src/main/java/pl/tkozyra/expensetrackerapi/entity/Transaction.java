package pl.tkozyra.expensetrackerapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tkozyra.expensetrackerapi.utils.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private String currency;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDate date;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
