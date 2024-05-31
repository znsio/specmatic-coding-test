package com.store.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import java.io.IOException

class StrictStringDeserializer : StringDeserializer() {
    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String? {
        val token = p.currentToken()
        if (token.isBoolean
            || token.isNumeric
            || !token.toString().equals("VALUE_STRING", ignoreCase = true)
        ) {
            ctxt.reportInputMismatch<Any>(String::class.java, "%s is not a `String` value!", token.toString())
            return null
        }
        return super.deserialize(p, ctxt)
    }
}
