package com.store.controllers.exceptions;

import com.store.controllers.ProductsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidProductTypeException extends Throwable {
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    public InvalidProductTypeException(String invalidProductType, IllegalArgumentException e) {
        logger.error("Invalid product type: {}", invalidProductType, e);
    }
}
