package com.store.objects

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.store.helpers.ProductDeserializer
import org.springframework.lang.NonNull

data class Product(
    @JsonProperty("id")
    var id: Int? = 0,
    @JsonProperty("name")
    @JsonDeserialize(using = ProductDeserializer::class)
    val name: String,
    @JsonProperty("type")
    val type: ProductType,
    @JsonProperty("inventory")
    @NonNull
    val inventory: Number
)