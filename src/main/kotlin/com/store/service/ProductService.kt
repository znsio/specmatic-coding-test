package com.store.service

import com.store.models.ProductDetails
import com.store.models.Type
import com.store.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.validation.Valid

@Service
class ProductService(
        private val productRepository: ProductRepository
) {
    fun findProductsBy(type: Type?) = productRepository.findBy(type)


    fun add(@Valid productDetails: ProductDetails): Int {
        return productRepository.save(productDetails)
    }

}