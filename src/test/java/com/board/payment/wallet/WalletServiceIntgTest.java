package com.board.payment.wallet;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class WalletServiceIntgTest {

    @Autowired
    WalletService walletService;

    @Test
    @Transactional //테스트 이후 db값을 정리한다.
    public void 지갑을_생성한다(){

        CreateWalletRequest request = new CreateWalletRequest(201L);

        CreateWalletResponse response = walletService.createWallet(request);

        Assertions.assertNotNull(response);
        System.out.println(response);
    }
}