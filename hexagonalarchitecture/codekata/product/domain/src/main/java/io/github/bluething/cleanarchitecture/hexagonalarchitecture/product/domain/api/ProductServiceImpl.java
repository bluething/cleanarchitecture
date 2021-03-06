package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;

import java.util.List;

public final class ProductServiceImpl implements ProductService {
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
        if (productCheck != null) {
            throw new IllegalArgumentException("Product already exist");
        }

        return repository.addProduct(product);
    }

    @Override
    public void deleteProduct(String code) {
        Product productByCode = repository.findProductByCode(code);
        if (productByCode == null) {
            throw new IllegalArgumentException("Product with code " + code+ " doesn't exist");
        }

        repository.deleteProduct(productByCode.getId());
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAllProducts();
    }
}
