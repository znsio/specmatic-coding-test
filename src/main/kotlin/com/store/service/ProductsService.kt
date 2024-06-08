package com.store.service

import com.store.domain.Product
import com.store.repository.ProductsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductsService(@Autowired private val productRepository: ProductsRepository) {
    fun save(product: Product): Product {
        return productRepository.save(product)
    }
}