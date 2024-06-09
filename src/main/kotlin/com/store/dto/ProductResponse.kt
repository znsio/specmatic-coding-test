package com.store.dto

import com.store.domain.Product

data class ProductResponse(
    val id: Int,
) {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!
            )
        }
    }
}