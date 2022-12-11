package com.info.groove.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;
import com.info.groove.mapper.AddressMapper;
import com.info.groove.repository.IAddressDao;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressDao addressDao;

    @Override
    public AddressDTO save(AddressDTO address) {
        Address commonAddress = AddressMapper.dtoToEntity(address);
        commonAddress = addressDao.save(commonAddress);
        address = AddressMapper.entityToDTO(commonAddress);
        return address;
    }

}
