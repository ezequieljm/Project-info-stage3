package com.info.groove.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;
import com.info.groove.mapper.AddressMapper;
import com.info.groove.repository.IAddressRepository;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressReposotory;

    @Override
    public AddressDTO save(AddressDTO address) {
        Address commonAddress = AddressMapper.dtoToEntity(address);
        commonAddress = addressReposotory.save(commonAddress);
        address = AddressMapper.entityToDTO(commonAddress);
        return address;
    }

}
