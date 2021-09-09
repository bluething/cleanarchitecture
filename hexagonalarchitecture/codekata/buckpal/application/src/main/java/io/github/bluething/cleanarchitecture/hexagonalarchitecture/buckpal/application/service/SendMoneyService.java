package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.service;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.in.SendMoneyUseCase;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.AccountLock;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.LoadAccount;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.UpdateAccountState;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {
    private final LoadAccount loadAccount;
    private final AccountLock accountLock;
    private final UpdateAccountState updateAccountState;
    private final MoneyTransferProperties moneyTransferProperties;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        checkThreshold(command);

        LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        Account sourceAccount = loadAccount.load(
                command.getSourceAccountId(),
                baselineDate);

        Account targetAccount = loadAccount.load(
                command.getTargetAccountId(),
                baselineDate);

        Account.AccountId sourceAccountId = sourceAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        Account.AccountId targetAccountId = targetAccount.getId()
                .orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

        accountLock.lockAccount(sourceAccountId);
        if (!sourceAccount.withdraw(command.getMoney(), targetAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            return false;
        }

        accountLock.lockAccount(targetAccountId);
        if (!targetAccount.deposit(command.getMoney(), sourceAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            accountLock.releaseAccount(targetAccountId);
            return false;
        }

        updateAccountState.updateActivities(sourceAccount);
        updateAccountState.updateActivities(targetAccount);

        accountLock.releaseAccount(sourceAccountId);
        accountLock.releaseAccount(targetAccountId);
        return true;
    }
    private void checkThreshold(SendMoneyCommand command) {
        if(command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())){
            throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(), command.getMoney());
        }
    }
}
