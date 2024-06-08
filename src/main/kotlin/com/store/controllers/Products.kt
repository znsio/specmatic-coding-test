package com.store.controllers

import com.store.objects.Product
import com.store.objects.ProductResponse
import com.store.objects.ProductType
import com.store.repositories.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class Products {
    private val productRepository = ProductRepository()

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) type: ProductType?) : ResponseEntity<Array<Product>> {
        var productType = type
        if (productType == null) {
            productType = ProductType.other
        }
        val products = productRepository.getProducts(productType)
        return ResponseEntity(products,HttpStatus.OK)
    }

    @PostMapping("/products")
    fun addProducts(@RequestBody @Valid product: Product) : ResponseEntity<Any> {
        val id = productRepository.addProduct(product)
        return ResponseEntity(ProductResponse(id),HttpStatus.CREATED)
    }
}
