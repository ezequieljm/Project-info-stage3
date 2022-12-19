package com.info.groove.controllers;


import com.info.groove.dto.RecurrentTurnDTO;
import com.info.groove.entity.RecurrentTurn;
import com.info.groove.service.turns.recurrentturns.IRecurrentTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RecurrentTurnController.BASIS)
public class RecurrentTurnController {
    public static final String BASIS = "/recurrent-turn";

    @Autowired
    private IRecurrentTurnService recurrentTurnService;

    @GetMapping
    public String helloRecurrentEvents() {
        return "Hello recurrent events";
    }

    @GetMapping(value = "/{orgId}")
    public ResponseEntity<Map<String,Object>> getAllTurnsByOrg(
            @PathVariable Long orgId
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        List<RecurrentTurn> turns = recurrentTurnService.searchAllTurnsByOrganization(orgId);
        response.put("Turns list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgId}/{eventId}")
    public ResponseEntity<Map<String,Object>> getAllTurnsByOrg(
            @PathVariable Long orgId,
            @PathVariable Long eventId
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        List<RecurrentTurn> turns = recurrentTurnService.searchAllTurnByOrgAndEvent(orgId, eventId);
        response.put("Turns list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerTurn(
            @RequestBody RecurrentTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        RecurrentTurnDTO turn = recurrentTurnService.register(turnDto);
        response.put("Stored Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateTurn(
            @RequestBody RecurrentTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        RecurrentTurnDTO turn = recurrentTurnService.update(turnDto);
        response.put("Updated Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    // Logical deletion
    @PutMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> logicalDeletionTurn(
            @RequestBody RecurrentTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        RecurrentTurnDTO turn = recurrentTurnService.logicalDeletion(turnDto);
        response.put("Updated Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> deleteTurn(
            @RequestBody RecurrentTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        recurrentTurnService.delete(turnDto);
        response.put("Updated Turn", null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
