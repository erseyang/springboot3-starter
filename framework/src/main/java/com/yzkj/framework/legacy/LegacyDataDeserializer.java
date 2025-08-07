package com.yzkj.framework.legacy;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.IOException;

/**
 * 用于处理如果旧系统出现错误的时候。data返回的是string字符串的情况
 */
public class LegacyDataDeserializer extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (jsonParser.getCurrentToken().isStructStart()) {
            return jsonParser.readValueAs(JSONPObject.class);
        }
        return jsonParser.readValueAs(TextNode.class).textValue();
    }
}
