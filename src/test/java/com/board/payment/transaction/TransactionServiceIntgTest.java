package com.board.payment.transaction;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TransactionServiceIntgTest {

    @Autowired
    TransactionService transactionService;


    @Test
    @Transactional
    public void 결제를_생성한다(){
        PaymentTransactionRequest request = new PaymentTransactionRequest(1L, 201L,"course-1", new BigDecimal((10)));

        PaymentTransactionResponse response = transactionService.payment((request));


        Assertions.assertNotNull(response);
        System.out.println(response);
    }
}
