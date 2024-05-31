package com.store.services

import com.store.exceptions.ValidationException
import com.store.model.DB
import com.store.model.Id
import com.store.model.Product
import org.springframework.stereotype.Service

@Service
class ProductService {
    fun addProduct(product: Product): Id {
        DB.addProduct(product)
        return Id(product.id)
    }

    fun findProducts(name:String?, type:String?, status:String?): List<Product> {
        return DB.findProducts(name, type, status)
    }
}