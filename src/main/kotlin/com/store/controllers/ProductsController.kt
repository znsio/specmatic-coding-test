package com.store.controllers

import com.store.domain.Product
import com.store.dto.ProductResponse
import com.store.dto.ProductSaveRequest
import com.store.service.ProductsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/products")
class ProductsController(@Autowired private val productService: ProductsService) {

        @PostMapping
        fun save(@RequestBody request: ProductSaveRequest): ResponseEntity<ProductResponse> {
            val product = Product(name = request.name, type = request.type, inventory = request.inventory)
            val response = ProductResponse(id = productService.save(product).id!!)
            return ResponseEntity(response, HttpStatus.CREATED)
        }
}
