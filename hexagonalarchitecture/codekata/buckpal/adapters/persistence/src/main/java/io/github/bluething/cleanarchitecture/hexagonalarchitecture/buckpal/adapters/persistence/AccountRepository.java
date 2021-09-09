package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
}
