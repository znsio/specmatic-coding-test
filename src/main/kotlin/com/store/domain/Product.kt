package com.store.domain

data class Product(
    var id: Int? = 0,
    val name: String,
    val type: String,
    val inventory: Int,
    val cost:Int,
)