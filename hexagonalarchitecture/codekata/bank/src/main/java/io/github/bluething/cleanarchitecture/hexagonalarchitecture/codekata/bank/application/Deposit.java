package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application;

import java.math.BigDecimal;

public interface Deposit {
    boolean deposit(Long accountNo, BigDecimal depositAmount);
}
