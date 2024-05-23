package com.store.controllers

import com.store.models.Product
import com.store.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import org.springframework.validation.BindingResult

@RestController
@RequestMapping("/products")
class Products {

    @Autowired
    private lateinit var productService: ProductService

    @GetMapping
    fun getProductsByType(@RequestParam(name = "type", required = false, defaultValue = "all") type: String): ResponseEntity<Any> {
        return try {
            if(type == "all") {
                val products = productService.getAllProducts();
                return ResponseEntity(products, HttpStatus.OK);
            }

            val productType = try {
                Product.Type.valueOf(type.lowercase())
            } catch (e: IllegalArgumentException) {
                return ResponseEntity(mapOf("error" to "Invalid type parameterr"), HttpStatus.BAD_REQUEST)
            }

            val products = productService.getProductsByType(productType)
            if (products.isEmpty()) {
                ResponseEntity(mapOf("error" to "No products found"), HttpStatus.NOT_FOUND)
            } else {
                ResponseEntity(products, HttpStatus.OK)
            }
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping
    fun postProduct(@Valid @RequestBody product: Product, bindingResult: BindingResult): ResponseEntity<Any> {
        if (bindingResult.hasErrors()) {
            // Extracting error messages and returning them in the response
            println("binding error")
            val errors = bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
            return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
        }

        // Additional validation for `name`
        if (isInvalidName(product.name)) {
            return ResponseEntity(mapOf("error" to "Invalid name: must not be a number or boolean"), HttpStatus.BAD_REQUEST)
        }

        // Additional validation for `inventory`
        if (product.inventory <= 0 ) {
            return ResponseEntity(mapOf("error" to "Invalid inventory value: must be greater than 0"), HttpStatus.BAD_REQUEST)
        }

        // Additional validation for `cost`
        if (product.cost <= 0.0 ) {
            return ResponseEntity(mapOf("error" to "Invalid cost value: must be greater than 0.0"), HttpStatus.BAD_REQUEST)
        }

        return try {
            if (product.name.isNullOrEmpty()) {
                ResponseEntity(mapOf("error" to "Missing required fields"), HttpStatus.BAD_REQUEST)
            } else {
                println("product vals are " + product)
                val productId = productService.saveProduct(product)
                ResponseEntity(mapOf("id" to productId), HttpStatus.CREATED)
            }
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

     // Function to check if the name is invalid
     private fun isInvalidName(name: String): Boolean {
        return try {
            name.toIntOrNull() != null || name.toBooleanStrictOrNull() != null
        } catch (e: Exception) {
            false // Return false if any exception occurs during parsing, meaning it's a valid string
        }
    }
}
