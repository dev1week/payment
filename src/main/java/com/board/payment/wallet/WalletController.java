package com.board.payment.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/api/wallets")
    public CreateWalletResponse createWallet(@RequestBody CreateWalletRequest request){
        return walletService.createWallet(request);
    }

    @GetMapping("/api/users/{userId}/wallets")
    public FindWalletResponse findWalletByUserId(@PathVariable("userId") Long userId){
        return walletService.findWalletByUserId(userId);
    }

    @PostMapping("/api/wallets/add-balance")
    public AddBalanceWalletResponse addBalance(
            @RequestBody AddBalanceWalletRequest request
    ){
        return walletService.addBalanceWalletResponse(request);
    }
}
