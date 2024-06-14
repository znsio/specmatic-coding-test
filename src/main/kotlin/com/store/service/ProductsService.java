package com.store.service;

import com.store.model.Product;
import com.store.model.ProductDetails;
import com.store.model.ProductId;
import com.store.model.ProductType;

import java.util.*;

public class ProductsService {

    private Map<Integer, Product> products = new HashMap<>();
    private int lastId = 0;

    public ProductId createProduct(ProductDetails productDetails) {
        int id = ++lastId;

        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setInventory(productDetails.getInventory());
        newProduct.setName(productDetails.getName());
        newProduct.setType(productDetails.getType());
        newProduct.setCost(productDetails.getCost());

        products.put(id, newProduct);

        ProductId productId = new ProductId();
        productId.setId(newProduct.getId());

        return productId;
    }

    public List<Product> getProducts(ProductType productType) {
        if(productType == null) {
            return new ArrayList<>(products.values());
        } else {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products.values()) {
                if (product.getType().equals(productType)) {
                    filteredProducts.add(product);
                }
            }
            return filteredProducts;
        }
    }
}
