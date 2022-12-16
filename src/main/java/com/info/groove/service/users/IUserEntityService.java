package com.info.groove.service.users;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserEntityService {
    public UserEntityDTO searchByFullname(String firstname, String lastname);

    public UserEntityDTO searchByDni(int dni);

    public List<UserEntity> searchAllUsers();

    public List<UserEntity> searchAllActiveUsers();

    public UserEntityDTO save(UserEntityDTO userEntityDto);

    public UserEntityDTO updateUserEntity(UserEntityDTO userDto);

    public UserEntityDTO logicalDeletionUserEntity(UserEntityDTO userDto) throws UserNotFoundException;

    public void deleteUserEntity(UserEntityDTO userDto) throws  UserNotFoundException;

}
