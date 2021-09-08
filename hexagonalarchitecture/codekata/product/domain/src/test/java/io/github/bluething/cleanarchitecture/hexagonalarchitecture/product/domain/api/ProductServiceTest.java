package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;
    private ProductService service;
    private Product product;

    @BeforeEach
    private void setupEach() {
        service = new ProductServiceImpl(repository);
        product = new Product(UUID.randomUUID(), "123", "asd");
    }
}