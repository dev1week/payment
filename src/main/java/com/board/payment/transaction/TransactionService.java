package com.board.payment.transaction;


import com.board.payment.wallet.AddBalanceWalletRequest;
import com.board.payment.wallet.AddBalanceWalletResponse;
import com.board.payment.wallet.FindWalletResponse;
import com.board.payment.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final WalletService walletService;
    private final TransactionRepository transactionRepository;

    @Transactional
    public ChargeTransactionResponse charge(ChargeTransactionRequest request){
        if(transactionRepository.findTransactionByOrderId((request.orderId())).isPresent()){
            throw new RuntimeException("이미 충전되 거래입니다.");
        }

        final FindWalletResponse response = walletService.findWalletByUserId((request.userId()));

        if(response==null){
            throw new RuntimeException("사용자 지갑이 존재하지 않습니다.");
        }

        final AddBalanceWalletResponse wallet = walletService.addBalanceWalletResponse(
                                                new AddBalanceWalletRequest(response.id(), request.amount())
                                                );

        final Transaction transaction = Transaction.createChargeTransaction(
                request.userId(),
                wallet.id(),
                "태스트 충전" + UUID.randomUUID(),
                request.amount()
        );
        transactionRepository.save(transaction);
        return new ChargeTransactionResponse(wallet.id(), wallet.balance());
    }

    public PaymentTransactionResponse payment(PaymentTransactionRequest request){


        if(transactionRepository.findTransactionByOrderId((request.courseId())).isPresent()){
            throw new RuntimeException("이미 결제된 강좌입니다.");
        }

        final FindWalletResponse response = walletService.findWalletByWalletId((request.walletId()));


        final AddBalanceWalletResponse wallet = walletService.addBalanceWalletResponse(
                new AddBalanceWalletRequest(response.id(), request.amount().negate())
        );

        final Transaction transaction = Transaction.createPaymentTransaction(
                request.userId(),
                wallet.id(),
                "태스트 충전" + UUID.randomUUID(),
                request.amount()
        );
        transactionRepository.save(transaction);
        return new PaymentTransactionResponse(wallet.id(), wallet.balance());
    }
}
