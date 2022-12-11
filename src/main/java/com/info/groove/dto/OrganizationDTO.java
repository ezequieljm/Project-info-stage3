package com.info.groove.dto;

import com.info.groove.entity.Address;

public class OrganizationDTO {
    
    private Long idOrg;

    private String email;

    private String orgName;

    private String phone;

    private String cuit;

    private String orgKey;

    private Address idAddress;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Long idOrg, String email, String orgName, String phone, String cuit, String orgKey,
            Address idAddress) {
        this.idOrg = idOrg;
        this.email = email;
        this.orgName = orgName;
        this.phone = phone;
        this.cuit = cuit;
        this.orgKey = orgKey;
        this.idAddress = idAddress;
    }

    public Long getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Long idOrg) {
        this.idOrg = idOrg;
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

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }

}
