package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;
    private ProductService service;
    private Product product;
    private UUID id;
    private String code;

    @BeforeEach
    private void setupEach() {
        service = new ProductServiceImpl(repository);
        id = UUID.randomUUID();
        code = "123";
        product = new Product(id, "123", "asd");
    }

    @Test
    public void createProductThrowsIllegalArgumentExceptionWhenProductIdIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createProduct(new Product(null, "", "")));
    }

    @Test
    public void createProductSuccessWhenThereAreNoProductWithSameId() {
        Mockito.when(repository.findProductByCode(code)).thenReturn(null);
        Mockito.when(repository.addProduct(product)).thenReturn(product);

        assertEquals(product, service.createProduct(product));
    }
}