package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public final class Product {
    private final UUID id;
    private final String code;
    private final String label;
}
