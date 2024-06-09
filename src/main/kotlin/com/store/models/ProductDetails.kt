package com.store.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.store.configurations.ProductDeserializer
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@JsonIgnoreProperties
@JsonDeserialize(using = ProductDeserializer::class)
data class ProductDetails(
        @JsonProperty("name")
        @field:NotBlank(message = "Name must not be blank")
        @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
        val name: String,
        @JsonProperty("inventory")
        val inventory: Int,
        @JsonProperty("type")
        val type: Type,
        val cost: Int
) {

    @JsonProperty("id")
    var id: Int = 0
}

