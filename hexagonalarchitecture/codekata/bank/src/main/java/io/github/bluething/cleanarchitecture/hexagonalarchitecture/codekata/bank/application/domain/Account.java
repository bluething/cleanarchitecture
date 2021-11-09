package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
class Account {
    private final long accountNo;
    private final BigDecimal accountBalance;

    Account(long accountNo, BigDecimal accountBalance) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public Account withdrawAmount(BigDecimal withdrawalAmount) throws Exception {
        if (accountBalance.compareTo(withdrawalAmount) < 0) {
            throw new IllegalArgumentException("Balance not enough");
        }

        return new Account(accountNo, accountBalance.subtract(withdrawalAmount));
    }

    public Account depositAmount(BigDecimal depositAmount) {
        return new Account(accountNo, accountBalance.add(depositAmount));
    }
}
