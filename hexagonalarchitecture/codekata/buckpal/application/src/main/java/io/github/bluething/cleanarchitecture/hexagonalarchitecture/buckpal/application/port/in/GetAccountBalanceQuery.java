package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.in;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(Account.AccountId accountId);
}
