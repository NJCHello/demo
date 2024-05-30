package com.example.spi.service;

import java.util.ServiceLoader;

/**
 * @author chenghao
 * @since 2024/5/30 09:02
 */
public interface Serializer {

    String serialize(Object obj);

    Object deSerialize(String str);

    static Serializer load() {
        ServiceLoader<Serializer> cacheServiceLoader = ServiceLoader.load(Serializer.class);
        return cacheServiceLoader.iterator().next();
    }
}
