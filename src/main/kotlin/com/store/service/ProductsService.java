package com.store.service;

import com.store.model.Product;
import com.store.model.ProductDetails;
import com.store.model.ProductId;
import com.store.model.ProductType;

import java.util.*;
import java.util.stream.Collectors;

public class ProductsService {

    private final Map<Integer, Product> products = new HashMap<>();
    private int lastId = 0;

    public ProductId createProduct(ProductDetails productDetails) {
        int id = ++lastId;

        ProductId newProductId = new ProductId(id);
        Product newProduct = new Product(newProductId, productDetails);
        products.put(id, newProduct);

        return newProductId;
    }

    public List<Product> getProducts(Optional<ProductType> productType) {
        return productType.map(type -> products.values().stream()
                        .filter(product -> product.type.equals(type))
                        .collect(Collectors.toList()))
                .orElseGet(() -> new ArrayList<>(products.values()));
    }
}
