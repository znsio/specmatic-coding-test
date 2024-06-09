package com.store.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@JsonIgnoreProperties
@JsonDeserialize(using = ProductDeserializer::class)
data class ProductDetails(
        @JsonProperty("name")
        @field:NotBlank(message = "Name must not be blank")
        @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
        val name: String,
        @JsonProperty("inventory")
        val inventory: Int,
        @JsonProperty("type")
        val type: Type
) {

        @JsonProperty("id")
        var id: Int= 0
}

class ProductDeserializer : JsonDeserializer<ProductDetails>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): ProductDetails {
                val node: JsonNode = p.codec.readTree(p)

                val nameNode = node.get("name")
                val name = if (nameNode.isTextual) {
                        nameNode.asText()
                } else {
                        throw IllegalArgumentException("Invalid type for name")
                }

                val type = node.get("type")?.asText() ?: throw IllegalArgumentException("Invalid type for type")
                val inventory = node.get("inventory")?.asInt() ?: throw IllegalArgumentException("Invalid type for inventory")

                return ProductDetails(name, inventory, Type.valueOf(type))
        }
}