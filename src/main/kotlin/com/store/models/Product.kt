package com.store.models

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Min

data class Product(
    var id: Int? = null,
    
    @NotBlank(message = "Product name cannot be null or empty")
    var name: String,

    @NotNull(message = "Product type cannot be null")
    var type: Type,

    @Min(value = 1, message = "Inventory must be greater than zero")
    var inventory: Int,

    // @Min(value = 0.1, message = "Cost must be greater than zero")
    var cost: Double

) {
    enum class Type {
        book, food, gadget, other
    }
}
