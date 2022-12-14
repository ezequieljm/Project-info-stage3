package com.info.groove.dto;

import javax.validation.constraints.NotEmpty;

import com.info.groove.entity.Address;

public class OrganizationDTO {
    
    // @NotEmpty
    private Long orgId;

    @NotEmpty
    private String email;

    @NotEmpty
    private String orgName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String cuit;

    @NotEmpty
    private String orgKey;

    @NotEmpty
    private boolean orgStatus;

    private Address addressId;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Long orgId, String email, String orgName, String phone, String cuit, String orgKey,
            boolean orgStatus,
            Address addressId) {
        this.orgId = orgId;
        this.email = email;
        this.orgName = orgName;
        this.phone = phone;
        this.cuit = cuit;
        this.orgKey = orgKey;
        this.orgStatus = orgStatus;
        this.addressId = addressId;
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

    public boolean getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(boolean orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

}
