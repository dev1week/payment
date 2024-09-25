package com.board.payment.transaction;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Transaction {

    @Id
    private Long id;

    private Long userId;

    private Long walletId;

    private String orderId;


    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private BigDecimal amount;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
