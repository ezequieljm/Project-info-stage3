package com.info.groove.service;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserEntityService {
    public UserEntity searchByFullname(String firstname, String lastname);

    public Optional<UserEntity> searchByDni(int dni);

    public List<UserEntity> findAllUsers();

    public UserEntityDTO save(UserEntity userEntity);

    public UserEntityDTO updateUserEntity(UserEntity user, String key);

    public void deleteUserEntity(Long id, String key);

}
