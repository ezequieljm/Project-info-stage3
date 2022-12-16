package com.info.groove.controllers;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.service.events.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = EventController.BASIS)
public class EventController {
    public static final String BASIS = "/event";

    @Autowired
    private IEventService eventService;

    @GetMapping
    public String helloEvents() {
        return "Hello events";
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> getAllEvents() {
        Map<String,Object> response = new HashMap<String, Object>();
        List<Event> eventsOfOrganization = eventService.getAllEvents();
        response.put("All Events", eventsOfOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{orgId}")
    public ResponseEntity<Map<String,Object>> getAllEventsByOrg(
            @PathVariable Long orgId
    ) {
        Map<String,Object> response = new HashMap<String, Object>();
        List<Event> eventsOfOrganization = eventService.getAllEventsByOrganization(orgId);
        response.put("Events of Organization", eventsOfOrganization);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerEvent(
            @RequestBody @Valid EventDTO newEvent
    ) {
        Map<String,Object> response = new HashMap<String, Object>();
        EventDTO storedEvent = eventService.save(newEvent);
        response.put("Events of Organization", storedEvent);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateEvent(
            @RequestBody EventDTO event
    ) {
        Map<String,Object> response = new HashMap<String, Object>();
        EventDTO updatedEvent = eventService.updateEvent(event);
        response.put("Events of Organization", updatedEvent);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }


    // Logical deletion
    @PutMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> logicalDeletionEventByOrg(
            @RequestBody @Valid EventDTO eventDto
    ) {
        Map<String,Object> response = new HashMap<String, Object>();
        EventDTO deletedEvent = eventService.logicalDeletion(eventDto);
        response.put("Events of Organization", deletedEvent);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Map<String, Object>> deleteEventOfOrg(
            @RequestBody @Valid EventDTO eventDto
    ) {
        Map<String,Object> response = new HashMap<String, Object>();
        eventService.deleteEvent(eventDto);
        response.put("Event deleted", null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
