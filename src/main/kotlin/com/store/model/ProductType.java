package com.store.model;

public enum ProductType {
    book("book"),
    food("food"),
    gadget("gadget"),
    other("other");

    public final String value;

    ProductType(String value) {
        this.value = value;
    }
}
