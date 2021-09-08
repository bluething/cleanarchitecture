package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.repository;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.model.MySQLProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ProductRepository extends CrudRepository<MySQLProduct, UUID> {
    MySQLProduct findOneByCode(String code);
}
