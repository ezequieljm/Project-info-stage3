package com.info.groove.service.events;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;

import java.util.List;

public interface IEventService {

    public EventDTO save(EventDTO eventDto, String key) throws OrganizationKeyNotEqual;

    public EventDTO updateEvent(EventDTO eventDTO, String key) throws OrganizationKeyNotEqual;

    public List<Event> getAllEventsByOrganization(Long orgId) throws OrganizationNotFoundException;

    public void deleteEvent(Long eventId, String key) throws EventNotFoundException;
}
