package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;

public interface UpdateAccountState {
    void updateActivities(Account account);
}
