package com.example.spi.service;

/**
 * @author chenghao
 * @since 2024/5/30 09:04
 */
public class SerializerImpl1 implements Serializer{
    @Override
    public String serialize(Object obj) {
        return "SerializerImpl1:"+obj.toString();
    }

    @Override
    public Object deSerialize(String str) {
        return null;
    }
}
