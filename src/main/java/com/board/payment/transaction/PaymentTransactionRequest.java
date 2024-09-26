package com.board.payment.transaction;

import java.math.BigDecimal;

public record PaymentTransactionRequest(
        Long userId,
        Long walletId,
        String courseId,
        BigDecimal amount) {
}
