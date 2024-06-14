package com.store.model;

public enum ProductType {
    book("book"),
    food("food"),
    gadget("gadget"),
    other("other");

    private String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
