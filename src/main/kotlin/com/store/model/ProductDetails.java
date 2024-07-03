package com.store.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductDetails {
    @NotNull
    @JsonDeserialize(using = StringOnlyDeserializer.class)
    public String name;

    @NotNull
    public ProductType type;

    @NotNull
    @Min(1)
    public int inventory;

    @NotNull
    @Min(1)
    public double cost;

    public ProductDetails() {}

    public ProductDetails(String name, ProductType type, int inventory, double cost) {
        this.name = name;
        this.type = type;
        this.inventory = inventory;
        this.cost = cost;
    }
}
