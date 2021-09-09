package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.port.out;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;

import java.time.LocalDateTime;

public interface LoadAccount {
    Account load(Account.AccountId accountId, LocalDateTime baselineDate);
}
