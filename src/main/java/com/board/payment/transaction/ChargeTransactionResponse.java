package com.board.payment.transaction;

import java.math.BigDecimal;

public record ChargeTransactionResponse(Long walletId, BigDecimal balance) {
}
