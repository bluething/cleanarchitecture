package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.service;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Money;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.in.GetAccountBalanceQuery;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.LoadAccount;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class GetAccountBalance implements GetAccountBalanceQuery {
    private final LoadAccount loadAccount;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccount.load(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
