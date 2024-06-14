package com.store.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductDetails {
    @NotNull
    @JsonDeserialize(using = StringOnlyDeserializer.class)
    private String name;

    @NotNull
    private ProductType type;

    @NotNull
    @Min(1)
    private int inventory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "name=" + name  +
                ", type=" + type +
                ", inventory=" + inventory +
                '}';
    }
}
