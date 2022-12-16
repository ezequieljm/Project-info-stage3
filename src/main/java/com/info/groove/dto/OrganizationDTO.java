package com.info.groove.dto;

import com.info.groove.entity.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationDTO {

    @NotNull(message = "Id cannot be null")
    private Long orgId;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Name cannot be null")
    private String orgName;

    @NotNull(message = "Phone cannot be null")
    private String phone;

    @NotNull(message = "Cuit cannot be null")
    @Size(min = 13, max = 13)
    @Pattern(regexp = "(30|15|20|21)-[0-9]{8}-(4|3|2)", message = "The cuit is not correct")
    private String cuit;

    @NotNull(message = "Key cannot be null")
    @Size(min = 6, max = 12)
    private String orgKey;

    @NotNull(message = "Status cannot be null")
    private boolean orgStatus;

    @NotNull(message = "Address cannot be null")
    private AddressDTO address;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Long orgId, String email, String orgName, String phone, String cuit, String orgKey,
            boolean orgStatus,
            AddressDTO address) {
        this.orgId = orgId;
        this.email = email;
        this.orgName = orgName;
        this.phone = phone;
        this.cuit = cuit;
        this.orgKey = orgKey;
        this.orgStatus = orgStatus;
        this.address = address;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

}
