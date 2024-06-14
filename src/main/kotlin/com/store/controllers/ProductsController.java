package com.store.controllers;

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

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService = new ProductsService();
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    public ProductsController() {
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String type) {
        if (type != null) {
            ProductType productType;
            try {
                productType = ProductType.valueOf(type.toLowerCase());
                logger.info("hey productType:" + productType);
            } catch (IllegalArgumentException e) {
                logger.error("Invalid product type: {}", type, e);
                ErrorResponseBody errorResponse = new ErrorResponseBody();
                errorResponse.setTimestamp(LocalDateTime.now());
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                errorResponse.setError("Invalid product type");
                errorResponse.setPath(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
                return ResponseEntity.badRequest().body(errorResponse);
            }

            List<Product> products = productsService.getProducts(productType);
            logger.info("Retrieved products of type: {}", type);
            return ResponseEntity.ok(products);
        } else {
            List<Product> products = productsService.getProducts(null);
            logger.info("Retrieved all products");
            return ResponseEntity.ok(products);
        }
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDetails productDetails) {
        if (productDetails.getName() == null || productDetails.getType() == null || productDetails.getInventory() <= 0) {
            throw new IllegalArgumentException("Invalid product details");
        }

        ProductId newProductId = productsService.createProduct(productDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProductId.getId())
                .toUri();
        return ResponseEntity.created(location).body(newProductId);
    }
}
