package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.port.in;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Money;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

public interface SendMoneyUseCase {

    boolean sendMoney(SendMoneyCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

        @NotNull
        private final Account.AccountId sourceAccountId;

        @NotNull
        private final Account.AccountId targetAccountId;

        @NotNull
        private final Money money;

        public SendMoneyCommand(
                Account.AccountId sourceAccountId,
                Account.AccountId targetAccountId,
                Money money) {
            this.sourceAccountId = sourceAccountId;
            this.targetAccountId = targetAccountId;
            this.money = money;
            this.validateSelf();
        }
    }

}
