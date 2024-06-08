package com.store.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResponse(
    @JsonProperty("id") val id:Int,
)