package com.info.groove.mapper;

import com.info.groove.dto.AddressDTO;
import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Organization;

public class OrganizationMapper {
    
    public static Organization dtoToEntity(OrganizationDTO orgDto) {
        
        if (orgDto == null) return new Organization();

        Organization foo = new Organization();

        foo.setOrgId(orgDto.getOrgId());
        foo.setCuit(orgDto.getCuit());
        foo.setEmail(orgDto.getEmail());
        foo.setOrgName(orgDto.getOrgName());
        foo.setOrgKey(orgDto.getOrgKey());
        foo.setPhone(orgDto.getPhone());
        foo.setOrgStatus(orgDto.getOrgStatus());

        Address address = AddressMapper.dtoToEntity(orgDto.getAddress());
        foo.setAddress(address);

        return foo;

    }


    public static OrganizationDTO entityToDTO(Organization organization) {
        
        if (organization == null) return new OrganizationDTO();

        OrganizationDTO foo = new OrganizationDTO();

        foo.setOrgId(organization.getOrgId());
        foo.setCuit(organization.getCuit());
        foo.setEmail(organization.getEmail());
        foo.setOrgName(organization.getOrgName());
        foo.setOrgKey(organization.getOrgKey());
        foo.setPhone(organization.getPhone());
        foo.setOrgStatus(organization.getOrgStatus());

        AddressDTO addressDto = AddressMapper.entityToDTO(organization.getAddress());
        foo.setAddress(addressDto);

        return foo;
    }
}
