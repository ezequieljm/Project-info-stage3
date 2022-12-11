package com.info.groove.dto;

import javax.validation.constraints.NotEmpty;

public class AddressDTO {
    
    @NotEmpty
    private Long idAddress;

    @NotEmpty
    private String country;

    @NotEmpty
    private String state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private int numStreet;

    public AddressDTO() {
    }

    public AddressDTO(Long idAddress, String country, String state, String city, String street, int numStreet) {
        this.idAddress = idAddress;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.numStreet = numStreet;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
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

    public int getNumStreet() {
        return numStreet;
    }

    public void setNumStreet(int numStreet) {
        this.numStreet = numStreet;
    }

}
