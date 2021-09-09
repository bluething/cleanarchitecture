package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.adapters.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
@AllArgsConstructor
@Getter
public class ActivityJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private Long ownerAccountId;

    @Column
    private Long sourceAccountId;

    @Column
    private Long targetAccountId;

    @Column
    private Long amount;

}
