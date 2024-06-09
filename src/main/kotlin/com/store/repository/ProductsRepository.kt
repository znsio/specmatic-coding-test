package com.store.repository

import com.store.domain.Product
import org.springframework.stereotype.Repository

@Repository
class ProductsRepository {
    // performance can be improved making it map<String, list<Product>> storing based on type as query is done on type
    private val productMap = mutableMapOf<Int, Product>()
    private var counter = 1

    fun save(product: Product): Product {
        product.id = counter
        productMap[counter++] = product
        return product
    }

    fun get(type: String?): List<Product> {
        return productMap.values.filter { it.type == (type ?: it.type) }
    }
}