package com.store.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.store.domain.Product
import com.store.enums.ProductType
import javax.validation.constraints.Pattern

data class ProductSaveRequest(
    @field:Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("name") val name: String,
    @field:Pattern(regexp = "^[a-zA-Z]+$")
    @JsonProperty("type") val type: String,
    @field:Pattern(regexp = "^[0-9]+$")
    @JsonProperty("inventory") val inventory: String,
) {
    init {
        validateType(type)
        validateName(name)
    }

    fun toProject(): Product {
        return Product(
            name = name,
            type = type,
            inventory = inventory.toInt()
        )
    }

    companion object {
        fun validateType(type: String) {
            if (!ProductType.values().any { it.value == type }) {
                throw IllegalArgumentException(
                    "Invalid product type: $type. Must be one of ${
                        ProductType.values()
                            .joinToString(", ")
                    }"
                )
            }
        }

        fun validateName(name: String) {
            if (name == "true" || name == "false") throw IllegalArgumentException("invalid name")
        }
    }
}