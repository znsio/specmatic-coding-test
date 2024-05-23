package com.store.services

import com.store.models.Product
import org.springframework.stereotype.Service

@Service
class ProductService {
    private val productList = mutableListOf<Product>()

    fun getProductsByType(type: Product.Type): List<Product> {
        return productList.filter { it.type == type }
    }

    fun getAllProducts() : List<Product>{
        return productList;
    }

    fun saveProduct(product: Product): Int {
        product.id = generateId()
        productList.add(product)
        return product.id!!
    }

    private fun generateId(): Int {
        return productList.size + 1;
    }
}
