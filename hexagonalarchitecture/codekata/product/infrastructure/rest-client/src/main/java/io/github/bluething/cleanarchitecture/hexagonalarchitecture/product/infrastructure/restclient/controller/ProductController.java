package io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.infrastructure.restclient.controller;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.Product;
import io.github.bluething.cleanarchitecture.hexagonalarchitecture.product.domain.api.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping(path = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
    void createProduct(@RequestBody RestProduct product) {
        service.createProduct(product.toDomain());
    }
}
