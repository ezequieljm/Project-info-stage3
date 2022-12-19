package com.info.groove.controllers;

import com.info.groove.dto.UniqueTurnDTO;
import com.info.groove.entity.UniqueTurn;
import com.info.groove.service.turns.uniqueturns.IUniqueTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = UniqueTurnController.BASIS)
public class UniqueTurnController {
    public static final String BASIS = "/unique";

    @Autowired
    private IUniqueTurnService uniqueTurnService;

    @GetMapping(value = "/")
    public String helloUniqueTurns() {
        return "Hello unique turn";
    }

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<Map<String,Object>> getAllTurnByOrg(
            @PathVariable Long id
    ){

        Map<String,Object> response = new HashMap<String,Object>();
        List<UniqueTurn> turns = uniqueTurnService.searchAllUniqueTurnsByOrg(id);
        response.put("Turn list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/all/{eventId}/{orgId}")
    public ResponseEntity<Map<String,Object>> getAllTurnByEventAndOrg(
            @PathVariable Long eventId,
            @PathVariable Long orgId
    ){

        Map<String,Object> response = new HashMap<String,Object>();
        List<UniqueTurn> turns = uniqueTurnService.searchAllUniqueTurnsByOrgAndEvent(eventId,orgId);
        response.put("Turn list", turns);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerTurn(
            @RequestBody UniqueTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueTurnDTO turn = uniqueTurnService.register(turnDto);
        response.put("Stored Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateTurn(
            @RequestBody UniqueTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueTurnDTO turn = uniqueTurnService.update(turnDto);
        response.put("Updated Turn", turn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    //Logic delete
    @PutMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> deleteTurn(
            @RequestBody @Valid UniqueTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        UniqueTurnDTO deletedTurn = uniqueTurnService.logicalDeletion(turnDto);
        response.put("Logical deleted Turn", deletedTurn);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> delete(
            @RequestBody @Valid UniqueTurnDTO turnDto
    ) {
        Map<String,Object> response = new HashMap<String,Object>();
        uniqueTurnService.delete(turnDto);
        response.put("Deleted Turn", null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
