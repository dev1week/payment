package com.board.payment.wallet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "wallet", indexes = @Index(name = "idx_created_at", columnList = "createdAt"))
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long userId;

    private BigDecimal balance;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Wallet(Long userId){
        this.userId = userId;
        this.balance = new BigDecimal(0);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
