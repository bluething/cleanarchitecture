package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.port.out;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);
    void releaseAccount(Account.AccountId accountId);
}
