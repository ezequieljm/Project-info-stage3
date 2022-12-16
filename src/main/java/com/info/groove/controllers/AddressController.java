package com.info.groove.controllers;

import com.info.groove.dto.AddressDTO;
import com.info.groove.entity.Address;
import com.info.groove.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = AddressController.BASIS)
public class AddressController {
    public static final String BASIS = "/address";

    @Autowired
    private IAddressService addressService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> getAllAddress() {
        Map<String,Object> response = new HashMap<String,Object>();
        List<Address> addressList = addressService.getAllAddress();
        response.put("Registered new address", addressList);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerAddress(
            @RequestBody @Valid AddressDTO addressDTO
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        AddressDTO registeredAddress = addressService.save(addressDTO);
        response.put("Registered new address", registeredAddress);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
