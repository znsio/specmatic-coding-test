package com.store.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    @JsonProperty("id") var id: Int? = 0,
    @JsonProperty("name") val name: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("inventory") val inventory: Int,
)