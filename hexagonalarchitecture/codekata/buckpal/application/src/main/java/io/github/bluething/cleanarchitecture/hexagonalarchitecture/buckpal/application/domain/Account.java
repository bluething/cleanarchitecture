package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @Getter
    private final AccountId id;

    @Value
    public static class AccountId {
        private Long value;
    }
}
