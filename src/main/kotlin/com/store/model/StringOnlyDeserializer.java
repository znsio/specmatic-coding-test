package com.store.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringOnlyDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (!JsonToken.VALUE_STRING.equals(jsonParser.getCurrentToken())) {
            throw deserializationContext.wrongTokenException(jsonParser, String.class, JsonToken.VALUE_STRING, "No type conversion is allowed, string expected");
        } else {
            return jsonParser.getValueAsString();
        }
    }
}
