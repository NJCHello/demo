package com.example.spi.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenghao
 * @since 2024/5/30 10:18
 */
public class SerializerTest {

//    public static void main(String[] args) {
//        String serializeStr = Serializer.load().serialize(new Object());
//        System.out.println(serializeStr);
//    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.example.spi.service.SerializerImpl2");
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod("serialize",Object.class);
        String binder = (String) method.invoke(instance, new Object());
        System.out.println(binder);
    }
}
