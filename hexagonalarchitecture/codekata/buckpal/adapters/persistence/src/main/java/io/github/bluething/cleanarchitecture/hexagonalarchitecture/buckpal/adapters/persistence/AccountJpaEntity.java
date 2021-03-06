package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
class AccountJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
}
