package com.store.service

import com.store.domain.Product
import com.store.enums.ProductType
import com.store.repository.ProductsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductsService(@Autowired private val productRepository: ProductsRepository) {
    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun get(type: String?): List<Product> {
        validateType(type)
        return productRepository.get(type)
    }

    private fun validateType(type: String?) {
        if (type != null && !ProductType.values().any { it.value == type }) {
            throw IllegalArgumentException("Invalid product type")
        }
    }
}