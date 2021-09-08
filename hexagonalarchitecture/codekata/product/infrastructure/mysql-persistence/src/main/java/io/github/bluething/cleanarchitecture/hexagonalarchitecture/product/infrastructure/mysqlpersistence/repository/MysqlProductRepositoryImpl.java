package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.repository;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.repository.ProductRepository;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.model.MySQLProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class MysqlProductRepositoryImpl implements ProductRepository {

    private final io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.repository.ProductRepository repository;

    @Autowired
    public MysqlProductRepositoryImpl(io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.repository.ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product addProduct(Product product) {
        repository.save(new MySQLProduct().fromDomain(product));
        return product;
    }

    @Override
    public void deleteProduct(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        Iterable<MySQLProduct> source = repository.findAll();
        List<Product> target = new ArrayList<>();
        source.forEach(mySQLProduct -> {
            target.add(mySQLProduct.toDomain());
        });

        return target;
    }

    @Override
    public Product findProductByCode(String code) {
        MySQLProduct product = repository.findOneByCode(code);
        if (product != null) {
            return product.toDomain();
        }
        return null;
    }
}
