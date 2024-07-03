package com.store.controllers;

import com.store.controllers.exceptions.InvalidProductTypeException;
import com.store.model.*;
import com.store.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService = new ProductsService();
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    public ProductsController() {
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String type) throws InvalidProductTypeException {
        ProductType productType = null;
        if (type != null) {
            try {
                productType = ProductType.valueOf(type.toLowerCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid product type: {}", type, e);
                throw new InvalidProductTypeException("Invalid product type", e);
            }
        }

        List<Product> products = productsService.getProducts(Optional.ofNullable(productType));
        logger.info("Retrieved products of type: {}", type);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductId> createProduct(@Valid @RequestBody ProductDetails productDetails){
        ProductId newProductId = productsService.createProduct(productDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProductId)
                .toUri();
        return ResponseEntity.created(location).body(newProductId);
    }

    @ExceptionHandler({InvalidProductTypeException.class})
    public ResponseEntity<ErrorResponseBody> handleException(RuntimeException e) {
        ErrorResponseBody errorResponse = new ErrorResponseBody(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
