package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.restclient.configuration;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api.ProductService;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api.ProductServiceImpl;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence"
})
public class ApplicationConfiguration {

    private final ProductRepository repository;

    @Autowired
    public ApplicationConfiguration(ProductRepository repository) {
        this.repository = repository;
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(repository);
    }
}
