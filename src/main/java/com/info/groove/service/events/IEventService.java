package com.info.groove.service.events;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;

import java.util.List;

public interface IEventService {

    public EventDTO save(EventDTO eventDto, String organizationKey) throws OrganizationKeyNotEqual;

    public EventDTO updateEvent(EventDTO eventDTO) throws OrganizationKeyNotEqual;

    public List<Event> getAllEventsByOrganization(Long orgId) throws OrganizationNotFoundException;

    public EventDTO deleteEvent(Long eventId) throws EventNotFoundException, OrganizationKeyNotEqual;
}
