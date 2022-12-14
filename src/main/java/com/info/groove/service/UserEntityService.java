package com.info.groove.service;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.UserNotFoundException;
import com.info.groove.mapper.UserEntityMapper;
import com.info.groove.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService implements IUserEntityService {

    @Autowired
    private IUserEntityRepository userEntityRepository;


    @Override
    public UserEntity searchByFullname(String firstname, String lastname) throws UserNotFoundException {
        List<UserEntity> usersWithFirstname = userEntityRepository.findAllByFirstname(firstname);
        for (UserEntity user : usersWithFirstname) {
            if (user.getLastname().equals(lastname)){
                return new UserEntity(user.getUserId(),
                        user.getUserKey(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getUserStatus(),
                        user.getDni());
            }
        }
        throw new UserNotFoundException(String.format("User with fullname %s %s not found", firstname, lastname));
    }

    @Override
    public Optional<UserEntity> searchByDni(int dni) {
        return userEntityRepository.findByDni(dni);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntityDTO save(UserEntity userEntity) {
        return UserEntityMapper.entityToDto(userEntityRepository.save(userEntity));
    }

    @Override
    public UserEntityDTO updateUserEntity(UserEntity user, String key) {
        // First: We need to verify that this user is in the database. Handle error if user not exists
        Optional<UserEntity> maybeUser = userEntityRepository.findById(user.getUserId());

        // Return a exception
        if (maybeUser.isEmpty()) throw new UserNotFoundException(String.format("User not exists", user.getUserId()));

        // Second: Verify that the key is the same, Handle the error that the user key isn't the same
        UserEntity originalUser = maybeUser.get();

        // Return a exception
        if (!originalUser.getUserKey().equals(key))
            throw new UserNotFoundException(String.format("The Keys don't match: %s =/ %s",key,user.getUserKey()));

        // Thrid: Update user
        return UserEntityMapper.entityToDto(userEntityRepository.save(user));

    }

    @Override
    public UserEntityDTO deleteUserEntity(Long id, String key) throws UserNotFoundException {
        // First: We seek user by id
        Optional<UserEntity> maybeUser = userEntityRepository.findById(id);

        // Return a exception
        if (maybeUser.isEmpty())
            throw new UserNotFoundException("User not exists to delete.");

        // Second: Verify that the key is the same, Handle the error that the user key isn't the same
        UserEntity originalUser = maybeUser.get();

        // Return a exception
        if (!originalUser.getUserKey().equals(key))
            throw new UserNotFoundException("The keys are not equals");

        // Thrid: We delete user
//        userEntityRepository.deleteById(id);

        originalUser.setUserStatus(false);

        UserEntityDTO userDto = UserEntityMapper.entityToDto(userEntityRepository.save(originalUser));
        return userDto;
    }
}
