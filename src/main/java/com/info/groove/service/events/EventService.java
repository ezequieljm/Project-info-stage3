package com.info.groove.service.events;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.mapper.EventMapper;
import com.info.groove.repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepository eventRepository;

    @Override
    public EventDTO save(EventDTO eventDto, String key) throws OrganizationKeyNotEqual {
        // First: We verify if the organization key is valid and then convert dto to entity
        if (!eventDto.getOrganization().getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual("It is not possible to add event. the keys are not the same");

        // Second: We stored the event
        Event storedEvent = eventRepository.save(EventMapper.dtoToEntity(eventDto));

        return EventMapper.entityToDto(storedEvent);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDto, String key) throws OrganizationKeyNotEqual {
        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Optional<Event> event = eventRepository.findById(eventDto.getEventId());
        if (event.isEmpty())
            throw new EventNotFoundException("The event not exists");

        if (!eventDto.getOrganization().getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual("It is not possible to add event. the keys are not the same");

        // Second: We stored the event
        Event storedEvent = eventRepository.save(EventMapper.dtoToEntity(eventDto));

        return EventMapper.entityToDto(storedEvent);
    }

    @Override
    public List<Event> getAllEventsByOrganization(Long orgId) {
        List<Event> events = eventRepository.findAll();
        return events.stream().filter(e -> e.getOrganization().getOrgId().equals(orgId)).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(Long eventId, String key) throws EventNotFoundException {
        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty())
            throw new EventNotFoundException("The event not exists");

        if (!event.get().getOrganization().getOrgKey().equals(key))
            throw new OrganizationKeyNotEqual("It is not possible to add event. the keys are not the same");

        // Second: We delete Event
        eventRepository.deleteById(eventId);
    }
}
