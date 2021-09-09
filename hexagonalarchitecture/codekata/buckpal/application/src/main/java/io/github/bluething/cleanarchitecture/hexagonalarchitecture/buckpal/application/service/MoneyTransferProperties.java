package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.service;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyTransferProperties {
    private Money maximumTransferThreshold = Money.of(1_000_000L);
}
