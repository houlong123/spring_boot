package com.example.demo.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
 * 实现项目序列化
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];


    /**
     * 序列化
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        if (o == null) {
            return EMPTY_ARRAY;
        }
        return serializer.convert(o);
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        return deserializer.convert(bytes);
    }

    private boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }
}
