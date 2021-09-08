package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product addProduct(Product product);
    void deleteProduct(UUID productId);
    List<Product> findAllProducts();
}
