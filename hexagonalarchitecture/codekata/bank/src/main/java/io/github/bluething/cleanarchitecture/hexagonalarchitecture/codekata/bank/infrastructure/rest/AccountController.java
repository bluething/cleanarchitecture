package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.infrastructure.rest;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.Deposit;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    private final Deposit depositUseCase;
    private final Withdraw withdrawUseCase;

    @Autowired
    public AccountController(Deposit depositUseCase, Withdraw withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @PostMapping(value = "/{accountNo}/deposit/{depositAmount}")
    void deposit(@PathVariable final Long accountNo, @PathVariable final BigDecimal depositAmount) {
        depositUseCase.deposit(accountNo, depositAmount);
    }

    @PostMapping(value = "/{accountNo}/withdraw/{withdrawalAmount}")
    void withdraw(@PathVariable final Long accountNo, @PathVariable final BigDecimal withdrawalAmount) throws Exception {
        withdrawUseCase.withdraw(accountNo, withdrawalAmount);
    }
}
