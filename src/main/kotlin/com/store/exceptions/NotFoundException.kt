package com.store.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(private val validationErrorMessage: String = "") : RuntimeException(validationErrorMessage) {
}