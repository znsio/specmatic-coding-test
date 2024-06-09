package com.store.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime

@ResponseStatus(HttpStatus.BAD_REQUEST)
data class ProductDetailsException(
        val timeStamp: LocalDateTime = LocalDateTime.now(),
        override val message: String
) : Exception( message)
