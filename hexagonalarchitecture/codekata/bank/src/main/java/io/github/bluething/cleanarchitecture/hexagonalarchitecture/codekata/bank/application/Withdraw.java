package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application;

import java.math.BigDecimal;

public interface Withdraw {
    boolean withdraw(Long accountNo, BigDecimal withdrawalAmount);
}
