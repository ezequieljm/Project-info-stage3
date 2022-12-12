package com.info.groove.repository;

import com.info.groove.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserEntityRepository extends JpaRepository<UserEntity,Long> {

    public List<UserEntity> findAllByFirstname(String firstname);

    public Optional<UserEntity> findByDni(int dni);
}
