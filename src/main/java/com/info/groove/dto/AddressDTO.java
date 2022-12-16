package com.info.groove.dto;


import javax.validation.constraints.NotNull;

public class AddressDTO {

    @NotNull(message = "Country cannot be null")
    private String country;

    @NotNull(message = "State cannot be null")
    private String state;

    @NotNull(message = "City cannot be null")
    private String city;

    @NotNull(message = "Street cannot be null")
    private String street;

    @NotNull(message = "Street number cannot be null")
    private int streetNumber;

    public AddressDTO() {
    }

    public AddressDTO(Long addressId, String country, String state, String city, String street, int streetNumber) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
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
