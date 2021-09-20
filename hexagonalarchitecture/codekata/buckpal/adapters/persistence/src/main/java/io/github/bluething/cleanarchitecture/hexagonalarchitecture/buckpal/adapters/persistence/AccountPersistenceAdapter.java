package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.adapters.persistence;


import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Account;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Activity;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.LoadAccount;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.port.out.UpdateAccountState;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
public class AccountPersistenceAdapter implements LoadAccount, UpdateAccountState {
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account load(Account.AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity account =
                accountRepository.findById(accountId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities =
                activityRepository.findByOwnerSince(
                        accountId.getValue(),
                        baselineDate);

        Long withdrawalBalance = orZero(activityRepository
                .getWithdrawalBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        Long depositBalance = orZero(activityRepository
                .getDepositBalanceUntil(
                        accountId.getValue(),
                        baselineDate));

        return accountMapper.mapToDomainEntity(
                account,
                activities,
                withdrawalBalance,
                depositBalance);
    }

    private Long orZero(Long value){
        return value == null ? 0L : value;
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity));
            }
        }
    }
}
