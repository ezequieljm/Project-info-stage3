package com.info.groove.dto;

import javax.validation.constraints.NotEmpty;

public class AddressDTO {
    
    // @NotEmpty
    private Long addressId;

    @NotEmpty
    private String country;

    @NotEmpty
    private String state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private int streetNumber;

    public AddressDTO() {
    }

    public AddressDTO(Long addressId, String country, String state, String city, String street, int streetNumber) {
        this.addressId = addressId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

}
