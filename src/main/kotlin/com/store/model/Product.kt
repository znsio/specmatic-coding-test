package com.store.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.concurrent.atomic.AtomicInteger
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class Product(
    @field:NotNull @field:JsonDeserialize(using = StrictStringDeserializer::class) val name: String = "",
    @field:NotNull val type: String = "gadget",
    @field:NotNull @field:Positive val inventory: Int = 0,
    @field:NotNull @field:Positive val cost: Int = 0,
    val id: Int = idGenerator.getAndIncrement()
) {
    companion object {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}

enum class ProductType {
    book,
    food,
    gadget,
    other
}