package com.store.model

import com.store.exceptions.UnrecognizedTypeException
import javax.validation.ValidationException

object DB {
    private var PRODUCTS: MutableMap<Int, Product> =
        mutableMapOf(10 to Product("XYZ Phone", "gadget", 10, 10,200), 20 to Product("Gemini", "dog", 10, 20,200))

    fun addProduct(product: Product) {
        PRODUCTS[product.id] = product
    }

    private fun inventoryStatus(productid: Int): String {
        return when (PRODUCTS.getValue(productid).inventory) {
            0 -> "sold"
            else -> "available"
        }
    }

    fun findProducts(name: String?, type: String?, status: String?): List<Product> {
        if (type != null && type !in listOf("book", "food", "gadget", "other"))
            throw UnrecognizedTypeException(type)

        return PRODUCTS.filter { (id, product) ->
            product.name == name || product.type == type || inventoryStatus(id) == status
        }.values.toList()
    }
}
