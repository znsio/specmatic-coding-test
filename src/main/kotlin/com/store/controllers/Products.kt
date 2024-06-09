package com.store.controllers

import com.store.dto.ProductCreationResponse
import com.store.models.ProductDetails
import com.store.models.Type
import com.store.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class Products(
        private val productService: ProductService
) {

    @GetMapping("/products")
     fun  getProduct(@RequestParam type: Type?): List<ProductDetails> {
         return productService.findProductsBy(type)
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    fun create( @RequestBody @Valid productDetails: ProductDetails) : ProductCreationResponse {
              return ProductCreationResponse(productService.add(productDetails))
    }
}
