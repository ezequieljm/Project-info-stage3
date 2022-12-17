package com.info.groove.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;
import com.info.groove.mapper.AddressMapper;
import com.info.groove.repository.IAddressRepository;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressReposotory;

    public List<Address> getAllAddress() {
        return addressReposotory.findAll();
    }

    @Override
    public AddressDTO save(AddressDTO addressDto) {
        // Control if address exists
        List<Address> addressList = addressReposotory.findAll();
        for (Address address : addressList) {
            if (address.getCity().equals(addressDto.getCity()) &&
            address.getCountry().equals(addressDto.getCountry()) &&
            address.getState().equals(addressDto.getState()) &&
            address.getStreet().equals(addressDto.getStreet()) &&
            address.getStreetNumber() == addressDto.getStreetNumber()) {
                throw new RuntimeException("Address already exists");
            }
        }
        Address commonAddress = AddressMapper.dtoToEntity(addressDto);
        commonAddress.setAddressAvailable(true);
        commonAddress = addressReposotory.save(commonAddress);
        addressDto = AddressMapper.entityToDTO(commonAddress);
        return addressDto;
    }

}
