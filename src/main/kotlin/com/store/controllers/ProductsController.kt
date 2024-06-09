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
import javax.validation.Valid


@RestController
@RequestMapping("/products")
class ProductsController(@Autowired private val productService: ProductsService) {

    @PostMapping
    fun save(@Valid @RequestBody request: ProductSaveRequest): ResponseEntity<ProductResponse> {
        return productService.save(request.toProject())
            .let { ProductResponse.from(it) }
            .let { ResponseEntity(it, HttpStatus.CREATED) }
    }
}
