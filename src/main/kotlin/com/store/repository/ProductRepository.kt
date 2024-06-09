package com.store.repository

import com.store.models.ProductDetails
import com.store.models.Type
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    companion object {
        var id = 1
        val productDetailsLists = mutableListOf(
                ProductDetails("XYZ Phone", 2, type = Type.gadget, 123),
                ProductDetails("ABC Book", 4, type = Type.book, 21),
                ProductDetails("Pizza", 7, type =  Type.food, 33),
                ProductDetails("watch", 12, type = Type.other, 100),
        );
    }

    init {
        productDetailsLists.forEach {
            it.id = id++
        }
    }

     fun findBy(type: Type?) : List<ProductDetails> {
         if (type == null) return productDetailsLists
        return productDetailsLists.filter { it.type == type }
        }

    fun save(productDetails: ProductDetails) : Int {
        productDetails.id = id++
         productDetailsLists.add(productDetails)
        return productDetails.id
    }

}