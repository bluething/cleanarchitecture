package io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.configuration;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.domain.Money;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.buckpal.application.service.MoneyTransferProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BuckPalConfigurationProperties.class)
public class BuckpalConfiguration {
    @Bean
    public MoneyTransferProperties moneyTransferProperties(BuckPalConfigurationProperties buckPalConfigurationProperties){
        return new MoneyTransferProperties(Money.of(buckPalConfigurationProperties.getTransferThreshold()));
    }
}
