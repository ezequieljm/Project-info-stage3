package com.info.groove.controllers;


import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.info.groove.dto.OrganizationDTO;
import com.info.groove.mapper.OrganizationMapper;
import com.info.groove.service.organizations.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = OrganizationController.BASIS)
public class OrganizationController {

    public static final String BASIS = "/organization";

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public String helloFromOrganizationPath() {
        return "Hello from organization path";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> allOrganizations() {
        Map<String, Object> response = new HashMap<String, Object>();
		response.put("Organizations", organizationService.getAllOrganizations());
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrganizationDTO> searchById(
            @PathVariable Long id
    ) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.searchByOrganizationId(id));
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/org-name/{name}")
    public ResponseEntity<OrganizationDTO> searchByName(
            @PathVariable(value = "name") String orgName
    ) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.searchByOrganizationName(orgName));
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/org-cuit/{cuit}")
    public ResponseEntity<OrganizationDTO> searchByCuit(
            @PathVariable(value = "cuit") String orgCuit
    ) {
        OrganizationDTO response = OrganizationMapper.entityToDTO(organizationService.searchByOrganizationCuit(orgCuit));
        return new ResponseEntity<OrganizationDTO>(response, HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerOrganization(
            @RequestBody @Valid OrganizationDTO organizationDtoPost
    ) {

        Map<String,Object> response = new HashMap<>();
        OrganizationDTO newOrganization = organizationService.register(organizationDtoPost);
        response.put("Organization", newOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateOrganization(
        @RequestBody @Valid OrganizationDTO organizationDTO
    ) {

        Map<String,Object> response = new HashMap<>();
        OrganizationDTO updateOrganization = organizationService.updateOrg(organizationDTO);

        // Return the same organization object if the key aren't equals
        response.put("organization", updateOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }


    // Logical deletion
    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteOrganization(
            @RequestBody @Valid OrganizationDTO organizationDto
    ) {
        Map<String,Object> response = new HashMap<>();
        OrganizationDTO org = organizationService.logicalDeletionOrganization(organizationDto);
        response.put("Deleted organization", org);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    // Permanent deletion
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String,Object>> permanentDeleteOrganization(
            @RequestBody @Valid OrganizationDTO organizationDto
    ) {
        Map<String,Object> response = new HashMap<>();
        organizationService.deleteOrganization(organizationDto);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
