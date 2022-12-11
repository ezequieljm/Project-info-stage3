package com.info.groove.mapper;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;

public class AddressMapper {

    public static Address dtoToEntity(AddressDTO addressDto) {
        
        if (addressDto == null) return new Address();

        Address foo = new Address();

        foo.setIdAddress(addressDto.getIdAddress());
        foo.setCountry(addressDto.getCountry());
        foo.setCity(addressDto.getCity());
        foo.setNumStreet(addressDto.getNumStreet());
        foo.setState(addressDto.getState());
        foo.setStreet(addressDto.getStreet());

        return foo;

    }


    public static AddressDTO entityToDTO(Address address) {
        
        if (address == null) return new AddressDTO();

        AddressDTO foo = new AddressDTO();

        foo.setIdAddress(address.getIdAddress());
        foo.setCountry(address.getCountry());
        foo.setCity(address.getCity());
        foo.setNumStreet(address.getNumStreet());
        foo.setState(address.getState());
        foo.setStreet(address.getStreet());

        return foo;
    }

}
