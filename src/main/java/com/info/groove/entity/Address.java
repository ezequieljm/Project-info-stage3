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
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "country")
    private String country;

    @Column(name = "state_ad")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "num_street")
    private int numStreet;

    @OneToOne(mappedBy = "idAddress")
    private Organization organization;

    /*
     * Constructors
     */

    public Address() {
    }

    public Address(String country, String state, String city, String street, int numStreet) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.numStreet = numStreet;
    }

    /*
     * Getters and Setters
     */
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

    @JsonBackReference
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
