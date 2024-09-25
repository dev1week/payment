package com.board.payment.transaction;

import java.math.BigDecimal;

public record ChargeTransactionRequest(Long userId, BigDecimal amount) {
}
