package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.port.out;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;

public interface UpdateAccountState {
    void updateActivities(Account account);
}
