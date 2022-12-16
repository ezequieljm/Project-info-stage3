package com.info.groove.service.users;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.UserNotFoundException;
import com.info.groove.mapper.UserEntityMapper;
import com.info.groove.repository.IUserEntityRepository;
import com.info.groove.service.users.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEntityService implements IUserEntityService {

    @Autowired
    private IUserEntityRepository userEntityRepository;


    @Override
    public List<UserEntity> searchAllUsers() {
        return userEntityRepository.findAll();
    }

    public List<UserEntity> searchAllActiveUsers() {
        List<UserEntity> users = userEntityRepository.findAll();
        return users.stream().filter(u -> u.getUserStatus()).collect(Collectors.toList());
    }

    @Override
    public UserEntityDTO searchByFullname(String firstname, String lastname) throws UserNotFoundException {
        List<UserEntity> usersWithFirstname = userEntityRepository.findAllByFirstname(firstname);
        for (UserEntity user : usersWithFirstname) {
            if (user.getLastname().equals(lastname)) return UserEntityMapper.entityToDto(user);
        }
        throw new UserNotFoundException(String.format("User with fullname %s %s not found", firstname, lastname));
    }

    @Override
    public UserEntityDTO searchByDni(int dni) throws UserNotFoundException {
        Optional<UserEntity> maybeUser = userEntityRepository.findByDni(dni);
        if (maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("The user with dni %d not exists",dni));
        UserEntity originalUser = maybeUser.get();
        return UserEntityMapper.entityToDto(originalUser);
    }


    @Override
    public UserEntityDTO save(UserEntityDTO userEntityDto) {
        UserEntity user = UserEntityMapper.dtoToEntity(userEntityDto);
        UserEntity newUser = userEntityRepository.save(user);
        return UserEntityMapper.entityToDto(newUser);
    }

    @Override
    public UserEntityDTO updateUserEntity(UserEntityDTO userDto) {
        // First: We need to verify that this user is in the database. Handle error if user not exists
        Optional<UserEntity> maybeUser = userEntityRepository.findById(userDto.getUserId());

        if (maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("User with id %d not exists", userDto.getUserId()));

        // Second: Verify that the key is the same, Handle the error that the user key isn't the same
        UserEntity originalUser = maybeUser.get();
        String originalUserKey = originalUser.getUserKey();
        String key = userDto.getUserKey();

        if (!originalUser.getUserKey().equals(userDto.getUserKey()))
            throw new UserNotFoundException(String.format("The Keys don't match: %s =/ %s",key,originalUserKey));

        // Thrid: Update user
        UserEntity updatedUser = userEntityRepository.save(UserEntityMapper.dtoToEntity(userDto));
        return UserEntityMapper.entityToDto(updatedUser);

    }


    @Override
    public UserEntityDTO logicalDeletionUserEntity(UserEntityDTO userDto)
            throws UserNotFoundException {
        // First: We seek user by id
        Long id = userDto.getUserId();
        String key = userDto.getUserKey();
        Optional<UserEntity> maybeUser = userEntityRepository.findById(id);

        // Return a exception
        if (maybeUser.isEmpty()) throw new UserNotFoundException("User not exists to delete.");

        // Second: Verify that the key is the same, Handle the error that the user key isn't the same
        UserEntity originalUser = maybeUser.get();

        // Return a exception
        if (!originalUser.getUserKey().equals(key)) throw new UserNotFoundException("The keys are not equals");

        originalUser.setUserStatus(false);

        UserEntityDTO deletedUser = UserEntityMapper.entityToDto(userEntityRepository.save(originalUser));
        return deletedUser;
    }


    @Override
    public void deleteUserEntity(UserEntityDTO userDto)
            throws UserNotFoundException {
        // First: We seek user by id
        Long id = userDto.getUserId();
        String key = userDto.getUserKey();
        Optional<UserEntity> maybeUser = userEntityRepository.findById(id);

        // Return a exception
        if (maybeUser.isEmpty()) throw new UserNotFoundException("User not exists to delete.");

        // Second: Verify that the key is the same, Handle the error that the user key isn't the same
        UserEntity originalUser = maybeUser.get();

        // Return a exception
        if (!originalUser.getUserKey().equals(key)) throw new UserNotFoundException("The keys are not equals");

        // Thrid: We delete user
        userEntityRepository.deleteById(id);

    }

}
