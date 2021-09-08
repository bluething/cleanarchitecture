package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.restclient.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;

import java.util.UUID;

public class RestProduct {
    private final UUID id;
    private final String code;
    private final String label;

    @JsonCreator
    public RestProduct(@JsonProperty("id") UUID id,
                       @JsonProperty("code") String code,
                       @JsonProperty("label") String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    Product toDomain() {
        return new Product(id, code, label);
    }
}
