package com.store.repositories

import com.store.objects.Product
import com.store.objects.ProductType

class ProductRepository constructor(

) {
    var products: Array<Product>

    init {
        val book = Product(id = 0, name = "Harry Potter", type = ProductType.book, inventory=2)
        val gadget = Product(id = 1, name = "Apple Iphone", type = ProductType.gadget, inventory=4)
        val food = Product(id = 2, name = "Fruit Cake", type = ProductType.food, inventory=5)
        val other = Product(id = 3, name = "Table", type = ProductType.other, inventory=1)
        val other2 = Product(id = 4, name = "Pen", type = ProductType.other, inventory=9)
        products = arrayOf(book, gadget, food, other, other2)
    }
    fun getProducts(type: ProductType) : Array<Product> {
        return products.filter { product: Product -> product.type== type}.toTypedArray()
    }

    fun addProduct(product: Product) : Int{
        product.id = products.size
        products = products.plusElement(product)
        return product.id!!
    }

}