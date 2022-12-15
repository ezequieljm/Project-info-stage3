package com.info.groove.mapper;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import org.apache.catalina.User;

public class UserEntityMapper {

    public static UserEntity dtoToEntity(UserEntityDTO userEntityDto){

        if (userEntityDto == null) return new UserEntity();

        UserEntity foo = new UserEntity();

        foo.setUserId(userEntityDto.getUserId());
        foo.setDni(userEntityDto.getDni());
        foo.setFirstname(userEntityDto.getFirstname());
        foo.setLastname(userEntityDto.getLastname());
        foo.setUserKey(userEntityDto.getUserKey());
        foo.setUserStatus(userEntityDto.getUserStatus());

        return foo;
    }

    public static UserEntityDTO entityToDto(UserEntity userEntity) {

        if (userEntity == null) return new UserEntityDTO();

        UserEntityDTO foo = new UserEntityDTO();

        foo.setUserId(userEntity.getUserId());
        foo.setDni(userEntity.getDni());
        foo.setFirstname(userEntity.getFirstname());
        foo.setLastname(userEntity.getLastname());
        foo.setUserKey(userEntity.getUserKey());
        foo.setUserStatus(userEntity.getUserStatus());

        return foo;
    }
}
