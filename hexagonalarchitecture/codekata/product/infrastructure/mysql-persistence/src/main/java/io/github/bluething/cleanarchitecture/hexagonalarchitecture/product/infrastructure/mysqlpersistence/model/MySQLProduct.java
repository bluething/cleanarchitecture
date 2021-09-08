package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.mysqlpersistence.model;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class MySQLProduct {
    @Id
    private UUID id;
    private String code;
    private String label;

    public MySQLProduct fromDomain(Product product) {
        return new MySQLProduct(product.getId(), product.getCode(), product.getLabel());
    }

    public Product toDomain() {
        return new Product(id, code, label);
    }
}
