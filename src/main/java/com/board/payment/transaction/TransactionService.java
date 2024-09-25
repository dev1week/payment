package com.board.payment.transaction;


import com.board.payment.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final WalletService walletService;
    private final TransactionRepository transactionRepository;
    public ChargeTransactionResponse charge(ChargeTransactionRequest request){
        
        return null;
    }

    public ChargeTransactionResponse payment(ChargeTransactionRequest request){

        return null;
    }
}
