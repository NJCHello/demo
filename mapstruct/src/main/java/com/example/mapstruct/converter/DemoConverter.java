package com.example.mapstruct.converter;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoConverter extends BaseConverter<Entity, Vo>{

    DemoConverter INSTANCE = Mappers.getMapper(DemoConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "useTime", target ="useTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "useStatus", expression = "java(do2voUseStatus(entity.getUseStatus()))")
    })
    @Override
    Vo do2vo(Entity entity);

    default String do2voUseStatus(Integer useStatus) {
        if (null == useStatus) {
            return null;
        }
        Enum useStatusEnum = Enum.getByCode(useStatus);
        if (null == useStatusEnum) {
            return null;
        }
        return useStatusEnum.getName();
    }

    @Mappings({
            @Mapping(source = "userId", target = "id"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(source = "useTime", target ="useTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "useStatus", expression = "java(vo2doUseStatus(vo.getUseStatus()))")
    })
    @Override
    Entity vo2do(Vo vo);

    default Integer vo2doUseStatus(String useStatus) {
        if (StringUtils.isBlank(useStatus)) {
            return null;
        }
        Enum useStatusEnum = Enum.getByName(useStatus);
        if (null == useStatusEnum) {
            return null;
        }
        return useStatusEnum.getCode();
    }


}
