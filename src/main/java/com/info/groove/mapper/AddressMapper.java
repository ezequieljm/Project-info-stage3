package com.info.groove.mapper;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;

public class AddressMapper {

    public static Address dtoToEntity(AddressDTO addressDto) {
        
        if (addressDto == null) return new Address();

        Address foo = new Address();

        foo.setCountry(addressDto.getCountry());
        foo.setCity(addressDto.getCity());
        foo.setStreetNumber(addressDto.getStreetNumber());
        foo.setState(addressDto.getState());
        foo.setStreet(addressDto.getStreet());

        return foo;

    }


    public static AddressDTO entityToDTO(Address address) {
        
        if (address == null) return new AddressDTO();

        AddressDTO foo = new AddressDTO();

        foo.setCountry(address.getCountry());
        foo.setCity(address.getCity());
        foo.setStreetNumber(address.getStreetNumber());
        foo.setState(address.getState());
        foo.setStreet(address.getStreet());

        return foo;
    }

}
