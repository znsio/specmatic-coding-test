package com.store.repository

import com.store.domain.Product
import org.springframework.stereotype.Repository

@Repository
class ProductsRepository {
    private val productMap:MutableMap<String, Product> = mutableMapOf()
    private var counter = 1

    fun save(product: Product): Product {
        product.id = counter++
        productMap[product.name] = product
        return productMap[product.name]!!
    }
}