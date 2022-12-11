package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "address")
public class Address {

    /*
     * Attributes
     */
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "state_ad")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private int streetNumber;

    @OneToOne(mappedBy = "addressId")
    private Organization organization;

    /*
     * Constructors
     */

    public Address() {
    }

    public Address(String country, String state, String city, String street, int streetNumber) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    /*
     * Getters and Setters
     */
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

    @JsonBackReference
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
