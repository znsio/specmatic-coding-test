package com.store.model;

public class Product {
    public final int id;
    public final String name;
    public final ProductType type;
    public final int inventory;
    public final double cost;

    public Product(ProductId productId, ProductDetails details) {
        this.id = productId.id;
        this.name = details.name;
        this.type = details.type;
        this.inventory = details.inventory;
        this.cost = details.cost;
    }
}
