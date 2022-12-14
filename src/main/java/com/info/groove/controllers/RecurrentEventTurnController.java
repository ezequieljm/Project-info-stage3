package com.info.groove.controllers;

import javax.validation.Valid;

import com.info.groove.dto.RecurrentEventTurnDTO;
import com.info.groove.entity.RecurrentEventTurn;
import com.info.groove.service.turns.recurrentturns.IRecurrentEventTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RecurrentEventTurnController.BASIS)
public class RecurrentEventTurnController {
    public static final String BASIS = "/turns";

    @Autowired
    private IRecurrentEventTurnService recurrentEventTurnService;

    @GetMapping
    public String helloRecurrentEvents() {
        return "Hello recurrent events";
    }

    @GetMapping(value = "/{orgId}")
    public ResponseEntity<Map<String,Object>> getAllTurnsByOrg(@PathVariable Long orgId) {
        Map<String,Object> response = new HashMap<String,Object>();
        List<RecurrentEventTurn> turns = recurrentEventTurnService.searchAllTurnsByOrganization(orgId, true);
        response.put("Turns list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgId}/{eventId}")
    public ResponseEntity<Map<String,Object>> getAllTurnsByOrg(@PathVariable Long orgId, @PathVariable Long eventId) {
        Map<String,Object> response = new HashMap<String,Object>();
        List<RecurrentEventTurn> turns = recurrentEventTurnService.searchAllTurnByOrgAndEvent(orgId, eventId);
        response.put("Turns list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Map<String,Object>> registerTurn(@RequestBody @Valid RecurrentEventTurnDTO turnDto) {
        Map<String,Object> response = new HashMap<String,Object>();
        RecurrentEventTurnDTO turn = recurrentEventTurnService.save(turnDto);
        response.put("Stored Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{key}")
    public ResponseEntity<Map<String,Object>> updateTurn(@RequestBody @Valid RecurrentEventTurnDTO turnDto, @PathVariable String key) {
        Map<String,Object> response = new HashMap<String,Object>();
        RecurrentEventTurnDTO turn = recurrentEventTurnService.updateRecurrentEventTurn(turnDto,key);
        response.put("Updated Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String,Object>> deleteTurn(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<String,Object>();
        recurrentEventTurnService.deleteRecurrentEventTurn(id);
        response.put("Updated Turn", null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
