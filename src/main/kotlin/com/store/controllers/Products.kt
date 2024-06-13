package com.store.controllers

import com.store.models.ErrorResponseBody
import com.store.models.Product
import com.store.models.ProductDetails
import com.store.models.ProductId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping("/products")
class ProductsController {

    private val products = mutableListOf<Product>()
    private var currentId = 1

    @GetMapping
    fun getProducts(@RequestParam type: String?): ResponseEntity<Any> {
        return if (!listOf("gadget", "book", "food", "other", null, " ").contains(type)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseBody(
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                    status = HttpStatus.BAD_REQUEST.value(),
                    error = "Invalid product type",
                    path = "/products"
                )
            )
        } else {
            val filteredProducts = products.filter { it.type == type }
            ResponseEntity.ok(filteredProducts)
        }
    }

    @PostMapping
    fun createProduct(@RequestBody productDetails: ProductDetails): ResponseEntity<Any> {
        return if (productDetails.name.isNullOrEmpty() || productDetails.inventory <= 0 || productDetails.inventory > 100 || productDetails.type.isNullOrEmpty() ||
            productDetails.name.contains("[0-9]".toRegex()) || listOf("true", "false").contains(productDetails.name) || !listOf("gadget", "book", "food", "other").contains(productDetails.type) || productDetails.cost <= 0.0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseBody(
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                    status = HttpStatus.BAD_REQUEST.value(),
                    error = "Invalid product details",
                    path = "/products"
                )
            )
        } else {
            val product = Product(currentId++, productDetails.name, productDetails.type, productDetails.inventory, productDetails.cost)
            products.add(product)
            ResponseEntity.status(HttpStatus.CREATED).body(ProductId(product.id))
        }
    }
}