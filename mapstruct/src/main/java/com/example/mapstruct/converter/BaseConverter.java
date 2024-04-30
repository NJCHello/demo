package com.example.mapstruct.converter;

import java.util.List;

/**
 * do vo转换基类
 * @param <D>
 * @param <V>
 */
public interface BaseConverter<D,V> {

    V do2vo(D entity);

    D vo2do(V vo);

    List<V> do2vo(List<D> entity);

    List<D> vo2do(List<V> vo);
}
