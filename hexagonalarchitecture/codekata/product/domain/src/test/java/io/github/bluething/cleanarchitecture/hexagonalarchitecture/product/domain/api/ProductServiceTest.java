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
    public void createProductThrowsIllegalArgumentExceptionWithErrorMessageRelatedToCodeWhenProductIdIsEmpty() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> service.createProduct(new Product(null, "", "")));
        assertEquals("There is no code for the product", illegalArgumentException.getMessage());
    }

    @Test
    public void createProductSuccessWhenThereAreNoProductWithSameId() {
        Mockito.when(repository.findProductByCode(code)).thenReturn(null);
        Mockito.when(repository.addProduct(product)).thenReturn(product);

        assertEquals(product, service.createProduct(product));
    }

    @Test
    public void createProductThrowsIllegalArgumentExceptionWithErrorMessageRelatedToDuplicateEntityWhenProductAlreadyExist() {
        Mockito.when(repository.findProductByCode(code)).thenReturn(product);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> service.createProduct(product));
        assertEquals("Product already exist", illegalArgumentException.getMessage());
    }

    @Test
    public void deleteProductSuccessCallRepositoryWhenProductIsExist() {
        Mockito.when(repository.findProductByCode(code)).thenReturn(product);

        service.deleteProduct(code);

        Mockito.verify(repository).deleteProduct(product.getId());
    }
}