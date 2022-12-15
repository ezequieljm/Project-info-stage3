package com.info.groove.controllers;

import com.info.groove.dto.UniqueEventTurnDTO;
import com.info.groove.entity.UniqueEventTurn;
import com.info.groove.service.turns.uniqueturns.IUniqueEventTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = UniqueEventTurnController.BASIS)
public class UniqueEventTurnController {
    public static final String BASIS = "/unique";

    @Autowired
    private IUniqueEventTurnService uniqueEventTurnService;

    @GetMapping(value = "/")
    public String helloUniqueTurns() {
        return "Hello unique turn";
    }

    @GetMapping(value = "/all/{id}/{key}")
    public ResponseEntity<Map<String,Object>> getAllTurnByEventAndOrg(
            @PathVariable Long eventId,
            @PathVariable String orgKey
    ){

        Map<String,Object> response = new HashMap<String,Object>();
        List<UniqueEventTurn> turns = uniqueEventTurnService.searchAllUniqueTurnsByOrgAndEvent(eventId,orgKey);
        response.put("Turn list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/all/{key}")
    public ResponseEntity<Map<String,Object>> getAllTurnByOrg(
            @PathVariable Long eventId,
            @PathVariable String orgKey
    ){

        Map<String,Object> response = new HashMap<String,Object>();
        List<UniqueEventTurn> turns = uniqueEventTurnService.searchAllUniqueTurnByOrg(orgKey);
        response.put("Turn list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/{orgKey}")
    public ResponseEntity<Map<String,Object>> registerTurn(
            @RequestBody UniqueEventTurnDTO turnDto,
            @PathVariable String orgKey
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueEventTurnDTO turn = uniqueEventTurnService.registerUniqueTurn(turnDto,orgKey);
        response.put("Stored Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{key}")
    public ResponseEntity<Map<String,Object>> updateTurn(
            @RequestBody UniqueEventTurnDTO turnDto,
            @PathVariable String key
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueEventTurnDTO turn = uniqueEventTurnService.updateUniqueTurn(turnDto,key);
        response.put("Updated Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    //Logic delete
    @PutMapping(value = "/delete/{id}/{orgKey}")
    public ResponseEntity<Map<String,Object>> deleteTurn(
            @PathVariable Long id,
            @PathVariable String orgKey
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueEventTurnDTO turnDto = uniqueEventTurnService.deleteUniqueTurn(id,orgKey);
        response.put("Updated Turn", turnDto);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
