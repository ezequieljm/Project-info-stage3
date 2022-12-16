package com.info.groove.service.address;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;

import java.util.List;

public interface IAddressService {

    public List<Address> getAllAddress();
    public AddressDTO save(AddressDTO address);

}
