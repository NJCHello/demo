package com.example.spi.service;

/**
 * @author chenghao
 * @since 2024/5/30 10:18
 */
public class SerializerTest {

    public static void main(String[] args) {
        String serializeStr = Serializer.load().serialize(new Object());
        System.out.println(serializeStr);
    }
}
