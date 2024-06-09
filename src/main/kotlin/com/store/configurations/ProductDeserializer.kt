package com.store.configurations

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.store.exception.ProductDetailsException
import com.store.models.ProductDetails
import com.store.models.Type

class ProductDeserializer : JsonDeserializer<ProductDetails>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ProductDetails {

        try {
            val node: JsonNode = p.codec.readTree(p)

            val nameNode = node.get("name")
            val name = if (nameNode.isTextual) {
                nameNode.asText()
            } else {
                throw ProductDetailsException(message = "Invalid type for name")
            }
                val typeNode = node.get("type")
                val type = if (typeNode.isTextual) {
                        Type.valueOf(typeNode.asText())
                } else {
                        throw ProductDetailsException(message = "Invalid type")
                }
                val inventoryNode = node.get("inventory")
                val inventory = if (inventoryNode.isInt) {
                        inventoryNode.asInt()
                }
                else {
                        throw ProductDetailsException(message = "Invalid Int")
                }
            val costNode = node.get("cost")
             val cost = if (costNode.isInt) {
                 inventoryNode.asInt()
            }
            else {
                throw ProductDetailsException(message = "Invalid Int")
            }
            return ProductDetails(name, inventory, type, cost)
        } catch (e: Exception) {
            throw ProductDetailsException(message = e.localizedMessage)
        }

    }
}