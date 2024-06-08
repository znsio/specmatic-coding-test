package com.store.objects

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResponse(
    @JsonProperty("id")
    var id: Int,
)
