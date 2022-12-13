package com.info.groove.controllers;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.repository.IAddressRepository;
import com.info.groove.service.events.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = EventController.BASIS)
public class EventController {
    public static final String BASIS = "/events";

    @Autowired
    private IEventService eventService;

    @GetMapping
    public String helloEvents() {
        return "Hello events";
    }

    @GetMapping(value = "/{orgId}")
    public ResponseEntity<Map<String,Object>> getAllEventsByOrg(@PathVariable Long orgId) {
        Map<String,Object> response = new HashMap<String, Object>();
        List<Event> eventsOfOrganization = eventService.getAllEventsByOrganization(orgId);
        response.put("Events of Organization", eventsOfOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/{key}")
    public ResponseEntity<Map<String,Object>> registerEvent(@PathVariable String key, @RequestBody EventDTO newEvent) {
        Map<String,Object> response = new HashMap<String, Object>();
        EventDTO storedEvent = eventService.save(newEvent, key);
        response.put("Events of Organization", storedEvent);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{key}")
    public ResponseEntity<Map<String,Object>> updateEvent(@PathVariable String key, @RequestBody EventDTO event) {
        Map<String,Object> response = new HashMap<String, Object>();
        EventDTO updatedEvent = eventService.updateEvent(event,key);
        response.put("Events of Organization", updatedEvent);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/{key}")
    public ResponseEntity<Map<String, Object>> deleteEventOfOrg(@PathVariable Long id, @PathVariable String key) {
        Map<String,Object> response = new HashMap<String, Object>();
        eventService.deleteEvent(id,key);
        response.put("Event deleted", null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
