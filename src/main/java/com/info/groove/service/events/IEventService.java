package com.info.groove.service.events;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.exceptions.DuplicateDataError;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;

import java.util.List;

public interface IEventService {
    public EventDTO register(EventDTO eventDto)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException, DuplicateDataError;

    public List<Event> getAllEvents();

    public List<Event> getAllEventsByOrganization(Long orgId) throws OrganizationNotFoundException;


    public EventDTO updateEvent(EventDTO eventDTO)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException, EventNotFoundException;

    public EventDTO logicalDeletion(EventDTO eventDto)
            throws EventNotFoundException, OrganizationKeyNotEqual, OrganizationNotFoundException;

    public void deleteEvent(EventDTO eventDto)
            throws EventNotFoundException, OrganizationKeyNotEqual, OrganizationNotFoundException;


}
