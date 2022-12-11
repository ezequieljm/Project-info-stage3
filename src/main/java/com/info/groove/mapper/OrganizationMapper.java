package com.info.groove.mapper;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Organization;

public class OrganizationMapper {
    
    public static Organization dtoToEntity(OrganizationDTO orgDto) {
        
        if (orgDto == null) return new Organization();

        Organization foo = new Organization();

        foo.setIdOrg(orgDto.getIdOrg());
        foo.setCuit(orgDto.getCuit());
        foo.setEmail(orgDto.getEmail());
        foo.setIdAddress(orgDto.getIdAddress());
        foo.setOrgName(orgDto.getOrgName());
        foo.setOrgKey(orgDto.getOrgKey());
        foo.setPhone(orgDto.getPhone());
        
        return foo;

    }


    public static OrganizationDTO entityToDTO(Organization organization) {
        
        if (organization == null) return new OrganizationDTO();

        OrganizationDTO foo = new OrganizationDTO();

        foo.setIdOrg(organization.getIdOrg());
        foo.setCuit(organization.getCuit());
        foo.setEmail(organization.getEmail());
        foo.setIdAddress(organization.getIdAddress());
        foo.setOrgName(organization.getOrgName());
        foo.setOrgKey(organization.getOrgKey());
        foo.setPhone(organization.getPhone());
        
        return foo;
    }
}
