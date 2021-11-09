package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.infrastructure.persistence;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.PersistAccount;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.RetrieveAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class AccountRepository implements PersistAccount, RetrieveAccount {

    private final SpringDataAccountRepository repository;

    @Autowired
    AccountRepository(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }

    @Override
    public Account load(Long accountNo) {
        return repository.findByAccountNo(accountNo);
    }
}
