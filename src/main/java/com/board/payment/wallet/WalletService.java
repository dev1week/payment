package com.board.payment.wallet;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.Find;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final BigDecimal BALANCE_LIMIT = new BigDecimal(1000000);

    private final WalletRepository walletRepository;

    @Transactional
    public CreateWalletResponse createWallet(CreateWalletRequest request) {


        boolean isWalletExist = walletRepository.existsByUserId(request.userId());
        if (isWalletExist) {
            throw new RuntimeException("이미 지갑이 존재합니다.");
        }
        final Wallet wallet = walletRepository.save(new Wallet(request.userId()));

       return new CreateWalletResponse(wallet.getId(), wallet.getUserId(), wallet.getBalance());
    }


    public FindWalletResponse findWalletByUserId(long userId) {
        return walletRepository.findById(userId).map(wallet-> new FindWalletResponse(wallet.getId(), wallet.getUserId(), wallet.getBalance(), wallet.getCreatedAt(), wallet.getUpdatedAt())).orElse(null);
    }


    public AddBalanceWalletResponse addBalanceWalletResponse(AddBalanceWalletRequest request){
        /*
            1. 잔액이 마이너스가 되면 오류가 발생해야한다.
            2. 최대 충전 한도는 10만원이다.
        * */
        final Wallet wallet = walletRepository.findById(request.walletId())
                .orElseThrow(()-> new RuntimeException("지갑이 없습니다."));

        BigDecimal balance = wallet.getBalance();
        balance = balance.add(request.amount());

        if(balance.compareTo(BALANCE_LIMIT) < 0){
            throw new RuntimeException("잔액이 충분하지 않습니다.");
        }

        wallet.setBalance(balance);
        walletRepository.save(wallet);


        return new AddBalanceWalletResponse(
                wallet.getId(), wallet.getUserId(), wallet.getBalance(), wallet.getCreatedAt(), wallet.getUpdatedAt()
                );
    }

}
