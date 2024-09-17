package com.example.demo.b2c.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author caozhixin
 * @date 2024/9/17 14:56
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "lastName", target = "familyName")
    User toEntity(UserDTO userDTO);

    @Mapping(source = "familyName", target = "lastName")
    UserDTO toDTO(User user);
}
