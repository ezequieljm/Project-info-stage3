package com.info.groove.mapper;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Organization;

public class OrganizationMapper {
    
    public static Organization dtoToEntity(OrganizationDTO orgDto) {
        
        if (orgDto == null) return new Organization();

        Organization foo = new Organization();

        foo.setOrgId(orgDto.getOrgId());
        foo.setCuit(orgDto.getCuit());
        foo.setEmail(orgDto.getEmail());
        foo.setAddressId(orgDto.getAddressId());
        foo.setOrgName(orgDto.getOrgName());
        foo.setOrgKey(orgDto.getOrgKey());
        foo.setPhone(orgDto.getPhone());
        
        return foo;

    }


    public static OrganizationDTO entityToDTO(Organization organization) {
        
        if (organization == null) return new OrganizationDTO();

        OrganizationDTO foo = new OrganizationDTO();

        foo.setOrgId(organization.getOrgId());
        foo.setCuit(organization.getCuit());
        foo.setEmail(organization.getEmail());
        foo.setAddressId(organization.getAddressId());
        foo.setOrgName(organization.getOrgName());
        foo.setOrgKey(organization.getOrgKey());
        foo.setPhone(organization.getPhone());
        
        return foo;
    }
}
