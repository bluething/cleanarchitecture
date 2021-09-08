package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    void deleteProduct(String code);
    List<Product> getAllProducts();
}
