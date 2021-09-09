package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);
    void releaseAccount(Account.AccountId accountId);
}
