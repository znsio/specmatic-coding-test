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

    @NotNull
    @Min(1)
    private double cost;

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "name=" + name  +
                ", type=" + type +
                ", inventory=" + inventory +
                ", cost=" + cost +
                '}';
    }
}
