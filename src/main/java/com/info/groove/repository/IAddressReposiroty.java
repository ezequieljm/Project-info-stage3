package com.info.groove.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.groove.entity.Address;

@Repository
public interface IAddressReposiroty extends JpaRepository<Address, Long> { }
