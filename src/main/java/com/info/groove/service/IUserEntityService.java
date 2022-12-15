package com.info.groove.service;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserEntityService {
    public UserEntity searchByFullname(String firstname, String lastname);

    public Optional<UserEntity> searchByDni(int dni);

    public List<UserEntity> searchAllUsers();

    public List<UserEntity> searchAllActiveUsers();

    public UserEntityDTO save(UserEntityDTO userEntityDto);

    public UserEntityDTO updateUserEntity(UserEntity user, String key);

    public UserEntityDTO deleteUserEntity(Long id, String key) throws UserNotFoundException;

}
