package com.example.spi.service;

/**
 * @author chenghao
 * @since 2024/5/30 09:05
 */
public class SerializerImpl2 implements Serializer {
    @Override
    public String serialize(Object obj) {
        return "SerializerImpl2";
    }

    @Override
    public Object deSerialize(String str) {
        return null;
    }
}
