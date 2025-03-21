package com.zfl19.satokendemospringboot.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 身份证ID脱敏JSON序列化器
 */
public class IDCardJsonSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (StrUtil.isNotEmpty(value)) {
            value = value.replaceAll("(\\d{6})\\d{10}(\\d{2})", "$1**********$2");
            jsonGenerator.writeString(value);
            return;
        }
        jsonGenerator.writeNull();
    }
}
