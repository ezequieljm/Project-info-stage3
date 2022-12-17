package com.info.groove.service.events;

import com.info.groove.dto.AddressDTO;
import com.info.groove.dto.EventDTO;
import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Event;
import com.info.groove.entity.Organization;
import com.info.groove.exceptions.DuplicateDataError;
import com.info.groove.exceptions.EventNotFoundException;
import com.info.groove.exceptions.OrganizationKeyNotEqual;
import com.info.groove.exceptions.OrganizationNotFoundException;
import com.info.groove.mapper.AddressMapper;
import com.info.groove.mapper.EventMapper;
import com.info.groove.repository.IAddressRepository;
import com.info.groove.repository.IEventRepository;
import com.info.groove.repository.IOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IAddressRepository addressRepository;


    @Override
    public EventDTO register(EventDTO eventDto)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException, DuplicateDataError {

        // We verify if event exists
        Optional<Event> maybeEvent = eventRepository.findById(eventDto.getEventId());
        if (maybeEvent.isPresent()) throw new DuplicateDataError("The event already exists");

        // We control that there is an organization exists and the keys are the same
        OrganizationDTO eventOrgDto = eventDto.getOrganization();
        Optional<Organization> maybeOrg = organizationRepository.findById(eventOrgDto.getOrgId());
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not found");

        // Verify if the keys are the same
        String originalOrgKey = maybeOrg.get().getOrgKey();
        String eventOrgKey = eventOrgDto.getOrgKey();
        if (!eventOrgKey.equals(originalOrgKey))
            throw new OrganizationKeyNotEqual("It is not possible to add event. the keys are not the same");

        //We work with address
        // Case if address exists
        AddressDTO addressDto = eventDto.getAddress();
        List<Address> addressList = addressRepository.findAll();
        for (Address address: addressList) {
            if (addressDto.getCity().equals(address.getCity()) &&
                    addressDto.getCountry().equals(address.getCountry()) &&
                    addressDto.getState().equals(address.getState()) &&
                    addressDto.getStreet().equals(address.getStreet()) &&
                    addressDto.getStreetNumber() == address.getStreetNumber()){

                if (!address.getAddressAvailable()) throw new RuntimeException("Address not available");

                Event newEvent = EventMapper.dtoToEntity(eventDto);
                address.setAddressAvailable(false);
                addressRepository.save(address);
                newEvent.setAddress(address);
                newEvent = eventRepository.save(newEvent);
                EventDTO newEventDto = EventMapper.entityToDto(newEvent);
                return newEventDto;
            }
        }

        // Case if address not exists
        Long maxId = 0L;
        if (addressList.isEmpty()) {
            maxId = 1L;
        } else {
            maxId = addressList.get(addressList.size() - 1).getAddressId() + 1;
        }

        // Add address
        Address newAddress = AddressMapper.dtoToEntity(addressDto);
        newAddress.setAddressAvailable(false);
        newAddress.setAddressId(maxId);
        addressRepository.save(newAddress);

        // We register a new event
        Event newEvent = EventMapper.dtoToEntity(eventDto);
        newEvent.setAddress(newAddress);
        newEvent = eventRepository.save(newEvent);
        EventDTO registeredEvent = EventMapper.entityToDto(newEvent);
        return registeredEvent;

    }

    @Override
    public EventDTO updateEvent(EventDTO eventDto)
            throws OrganizationKeyNotEqual, OrganizationNotFoundException, EventNotFoundException {

        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Optional<Event> event = eventRepository.findById(eventDto.getEventId());
        if (event.isEmpty()) throw new EventNotFoundException("The event not exists");

        // Virify if organization exists
        OrganizationDTO eventOrg = eventDto.getOrganization();
        String eventOrgKey = eventOrg.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(eventOrg.getOrgId());
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not exists");

        // Verify if the keys are the same
        Organization originalOrg = maybeOrg.get();
        String orgKey = originalOrg.getOrgKey();
        if (!eventOrgKey.equals(orgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        //We work with address
        // Case if address exists
        AddressDTO addressDto = eventDto.getAddress();
        List<Address> addressList = addressRepository.findAll();
        for (Address address: addressList) {
            if (addressDto.getCity().equals(address.getCity()) &&
                    addressDto.getCountry().equals(address.getCountry()) &&
                    addressDto.getState().equals(address.getState()) &&
                    addressDto.getStreet().equals(address.getStreet()) &&
                    addressDto.getStreetNumber() == address.getStreetNumber()){

                if (!address.getAddressAvailable()) throw new RuntimeException("Address not available");

                Event newEvent = EventMapper.dtoToEntity(eventDto);
                address.setAddressAvailable(false);
                addressRepository.save(address);
                newEvent.setAddress(address);
                newEvent = eventRepository.save(newEvent);
                EventDTO newEventDto = EventMapper.entityToDto(newEvent);
                return newEventDto;
            }
        }

        // Case if address not exists
        Long maxId = 0L;
        if (addressList.isEmpty()) {
            maxId = 1L;
        } else {
            maxId = addressList.get(addressList.size() - 1).getAddressId() + 1;
        }

        // Add address
        Address newAddress = AddressMapper.dtoToEntity(addressDto);
        newAddress.setAddressAvailable(false);
        newAddress.setAddressId(maxId);
        addressRepository.save(newAddress);

        // We register a new event
        Event newEvent = EventMapper.dtoToEntity(eventDto);
        newEvent.setAddress(newAddress);
        newEvent = eventRepository.save(newEvent);
        EventDTO registeredEvent = EventMapper.entityToDto(newEvent);
        return registeredEvent;

    }

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
    public EventDTO logicalDeletion(EventDTO eventDto)
            throws EventNotFoundException, OrganizationKeyNotEqual, OrganizationNotFoundException {

        // First: We verify if the event exists and organization key is valid, then convert dto to entity
        Long eventId = eventDto.getEventId();
        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        if (maybeEvent.isEmpty()) throw new EventNotFoundException("The event not exists");

        OrganizationDTO eventOrg = eventDto.getOrganization();
        Long orgId = eventOrg.getOrgId();
        String eventOrgKey = eventOrg.getOrgKey();
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        if (maybeOrg.isEmpty()) throw new OrganizationNotFoundException("The organization not exists");

        String orgKey = maybeOrg.get().getOrgKey();
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

        OrganizationDTO eventOrg = eventDto.getOrganization();
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
