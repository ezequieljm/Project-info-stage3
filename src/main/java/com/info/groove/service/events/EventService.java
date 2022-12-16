package com.info.groove.service.events;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;
import com.info.groove.entity.Organization;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;
import com.info.groove.mapper.EventMapper;
import com.info.groove.repository.IEventRepository;
import com.info.groove.repository.IOrganizationRepository;
import net.bytebuddy.description.type.TypeDescription;
import org.aspectj.weaver.ast.Or;
import org.aspectj.weaver.patterns.OrAnnotationTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    @Override
    public List<Event> getAllEventsByOrganization(Long orgId) {
        List<Event> events = eventRepository.findAll();
        List<Event> eventsOfOrg = events
                .stream()
                .filter(e -> e.getOrganization().getOrgId().equals(orgId))
                .collect(Collectors.toList());
        return eventsOfOrg;
    }

    @Override
    public EventDTO save(EventDTO eventDto)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException {
        // First: We verify if the organization key is valid and then convert dto to entity
        Organization eventOrg = eventDto.getOrganization();
        Optional<Organization> maybeOrg = organizationRepository.findById(eventOrg.getOrgId());
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not found");

        Organization originalOrganization = maybeOrg.get();
        String eventOrgKey = eventOrg.getOrgKey();
        if (!eventOrgKey.equals(originalOrganization.getOrgKey()))
            throw new OrganizationKeyNotEqual("It is not possible to add event. the keys are not the same");

        // Second: We stored the event
        Event storedEvent = eventRepository.save(EventMapper.dtoToEntity(eventDto));
        return EventMapper.entityToDto(storedEvent);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDto)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException, EventNotFoundException {
        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Optional<Event> event = eventRepository.findById(eventDto.getEventId());
        if (event.isEmpty()) throw new EventNotFoundException("The event not exists");

        Organization eventOrg = eventDto.getOrganization();
        String eventOrgKey = eventOrg.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(eventOrg.getOrgId());
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not exists");

        Organization originalOrg = maybeOrg.get();
        String orgKey = originalOrg.getOrgKey();
        if (!eventOrgKey.equals(orgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        // Second: We stored the event
        Event storedEvent = eventRepository.save(EventMapper.dtoToEntity(eventDto));
        return EventMapper.entityToDto(storedEvent);
    }

    @Override
    public EventDTO logicalDeletion(EventDTO eventDto)
            throws EventNotFoundException, OrganizationKeyNotEqual, OrganizationNotFoundException {

        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Long eventId = eventDto.getEventId();
        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        if (maybeEvent.isEmpty()) throw new EventNotFoundException("The event not exists");

        Organization eventOrg = eventDto.getOrganization();
        Long orgId = eventOrg.getOrgId();
        String eventOrgKey = eventOrg.getOrgKey();

        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not exists");

        Organization org = maybeOrg.get();
        String orgKey = org.getOrgKey();

        if (!orgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        // Second: We delete Event
        Event originalEvent = maybeEvent.get();
        originalEvent.setEventStatus(false);
        Event deletedEvent = eventRepository.save(originalEvent);
        return EventMapper.entityToDto(deletedEvent);
    }

    @Override
    public void deleteEvent(EventDTO eventDto)
            throws EventNotFoundException, OrganizationKeyNotEqual, OrganizationNotFoundException {
        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Long eventId = eventDto.getEventId();
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isEmpty()) throw new EventNotFoundException("The event not exists");

        Organization eventOrg = eventDto.getOrganization();
        Long orgId = eventOrg.getOrgId();
        String eventOrgKey = eventOrg.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not exists");

        Organization org = maybeOrg.get();
        String orgKey = org.getOrgKey();
        if (!orgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        // Second: We delete Event
        eventRepository.deleteById(eventId);
    }
}
