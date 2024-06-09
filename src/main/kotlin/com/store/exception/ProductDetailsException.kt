package com.store.exception

import java.time.LocalDateTime

data class ProductDetailsException(
        val timeStamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String,
        val path: String
)
