package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService implements Deposit, Withdraw {

    private final RetrieveAccount retrieveAccount;
    private final PersistAccount persistAccount;

    @Autowired
    public AccountService(RetrieveAccount retrieveAccount, PersistAccount persistAccount) {
        this.retrieveAccount = retrieveAccount;
        this.persistAccount = persistAccount;
    }

    @Override
    public boolean deposit(Long accountNo, BigDecimal depositAmount) {
        Account account = retrieveAccount.load(accountNo);
        persistAccount.save(account.depositAmount(depositAmount));
        return true;
    }

    @Override
    public boolean withdraw(Long accountNo, BigDecimal withdrawalAmount) throws Exception {
        Account account = retrieveAccount.load(accountNo);
        account.withdrawAmount(withdrawalAmount);
        return true;
    }
}
