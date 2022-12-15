package com.info.groove.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "organizations")
public class Organization {

    /*
     * Attributes
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    @NotNull(message = "Cannot be null")
    private Long orgId;

    @Column(name = "email")
    @NotNull(message = "Cannot be null")
    private String email;

    @Column(name = "org_name")
    @NotNull(message = "Cannot be null")
    private String orgName;

    @Column(name = "phone")
    @NotNull(message = "Cannot be null")
    private String phone;

    @Column(name = "cuit")
    @NotNull(message = "Cannot be null")
    @Size(min = 13, max = 13)
    @Pattern(regexp = "(30|15|20|21)-[0-9]{8}-(4|3|2)", message = "The cuit is not correct")
    private String cuit;

    @Column(name = "org_key")
    @NotNull(message = "Cannot be null")
    @Size(min = 6, max = 12)
    private String orgKey;

    @Column(name = "org_status")
    @NotNull(message = "Cannot be null")
    private boolean orgStatus;

    @OneToOne
    @JoinColumn(name = "address_id")
    @NotNull(message = "Cannot be null")
    private Address address;

    /*
     * Constructors
     */
    public Organization(String email, String orgName, String phone, String cuit, String orgKey, boolean orgStatus, Address address) {
        this.email = email;
        this.orgName = orgName;
        this.phone = phone;
        this.cuit = cuit;
        this.orgKey = orgKey;
        this.orgStatus = orgStatus;
        this.address = address;
    }

    public Organization() {
    }

    /*
     * Getters and setters
     */


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

    public boolean getOrgStatus() { return orgStatus; }

    public void setOrgStatus(boolean orgStatus) {
        this.orgStatus = orgStatus;
    }

    @JsonManagedReference
    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

}
