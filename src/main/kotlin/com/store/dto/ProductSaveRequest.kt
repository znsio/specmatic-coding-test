package com.store.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductSaveRequest (
    @JsonProperty("name") val name: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("inventory") val inventory: Int,
)