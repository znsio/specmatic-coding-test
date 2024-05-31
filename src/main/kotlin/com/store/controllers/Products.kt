package com.store.controllers

import com.store.exceptions.NotFoundException
import com.store.exceptions.ValidationException
import com.store.model.Id
import com.store.model.Product
import com.store.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val typesOfProducts = listOf("gadget", "book", "food", "other")

@RestController
open class Products {

    @Autowired
    lateinit var productService: ProductService

    @PostMapping("/products")
    fun create(@Valid @RequestBody newProduct: Product): ResponseEntity<Id> {
        val productId = productService.addProduct(newProduct.also {
            if(newProduct.type !in typesOfProducts)
                throw ValidationException("type must be one of ${typesOfProducts.joinToString(", ")}")
        })
        return ResponseEntity(productId, HttpStatus.CREATED)
    }

    @GetMapping("/products")
    fun search(
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "type", required = false) type: String?,
        @RequestParam(name = "status", required = false) status: String?
    ): ResponseEntity<List<Product>> {
        if (name == "unknown")
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        val products = productService.findProducts(name, type, status)
        return ResponseEntity(products, HttpStatus.OK)
    }
}
