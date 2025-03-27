package com.future.csamsserver.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @description
 * @dateTime 2025:03:08 19:17
 * @user Jenming
 */
public abstract class BaseDesensitizeSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        gen.writeString(doDesensitize(value));
    }

    protected abstract String doDesensitize(String value);
}

