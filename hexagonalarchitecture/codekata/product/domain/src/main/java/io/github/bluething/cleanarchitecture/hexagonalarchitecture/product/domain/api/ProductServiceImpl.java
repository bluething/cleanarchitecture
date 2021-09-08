package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("There is no code for the product");
        }

        Product productCheck = repository.findProductByCode(product.getCode());

        return repository.addProduct(product);
    }

    @Override
    public void deleteProduct(String code) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
