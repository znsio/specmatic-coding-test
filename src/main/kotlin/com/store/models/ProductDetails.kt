package com.store.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties
data class ProductDetails(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("inventory")
        val inventory: Int,
        @JsonProperty("type")
        val type: Type
) {

        @JsonProperty("id")
        var id: Int= 0
}