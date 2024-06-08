package com.store.helpers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.util.Locale

class ProductDeserializer : JsonDeserializer<String>() {

    override fun deserialize(parser: JsonParser, context: DeserializationContext): String? {
        if (parser.text.toIntOrNull() != null) {
            return null
        }
        return when (parser.text.lowercase(Locale.getDefault())) {
            "true" -> null
            "false" -> null
            else -> parser.text
        }
    }
}