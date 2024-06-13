package com.store.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDetails @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("inventory") val inventory: Int,
    @JsonProperty("cost") val cost: Float
)

data class ProductId(
    val id: Int
)

data class Product(
    val id: Int,
    val name: String,
    val type: String,
    val inventory: Int,
    val cost: Float
)

data class ErrorResponseBody(
    val timestamp: String,
    val status: Int,
    val error: String,
    val path: String
)