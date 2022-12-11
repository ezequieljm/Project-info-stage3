package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "organizations")
public class Organization {

    /*
     * Attributes
     */
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "email")
    private String email;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "org_key")
    private String orgKey;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address addressId;

    /*
     * Constructors
     */
    public Organization(String email, String orgName, String phone, String cuit, String orgKey, Address addressId) {
        this.email = email;
        this.orgName = orgName;
        this.phone = phone;
        this.cuit = cuit;
        this.orgKey = orgKey;
        this.addressId = addressId;
    }

    public Organization() {
    }

    /*
     * Getters and setters
     */

    @JsonManagedReference
    public Address getAddressId() {
        return addressId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getOrgKey() {
        return orgKey;
    }

    public void setOrgKey(String orgKey) {
        this.orgKey = orgKey;
    }

    public void setAddressId(Address idAddress) {
        this.addressId = idAddress;
    }
}
