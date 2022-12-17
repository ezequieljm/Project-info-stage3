package com.info.groove.mapper;

import com.info.groove.dto.AddressDTO;
import com.info.groove.dto.EventDTO;
import com.info.groove.dto.OrganizationDTO;
import com.info.groove.entity.Address;
import com.info.groove.entity.Event;
import com.info.groove.entity.Organization;

public class EventMapper {
    public static Event dtoToEntity(EventDTO eventDto) {
        if (eventDto == null) return new Event();

        Event event = new Event();

        event.setEventId(eventDto.getEventId());
        event.setEventName(eventDto.getEventName());
        event.setEventStatus(eventDto.getEventStatus());
        event.setCreationDate(eventDto.getCreationDate());
        event.setEventType(eventDto.getEventType());

        Address address = AddressMapper.dtoToEntity(eventDto.getAddress());
        event.setAddress(address);

        Organization org = OrganizationMapper.dtoToEntity(eventDto.getOrganization());
        event.setOrganization(org);

        return event;
    }

    public static EventDTO entityToDto(Event event) {
        if (event == null) return new EventDTO();

        EventDTO foo = new EventDTO();

        foo.setEventId(event.getEventId());
        foo.setEventName(event.getEventName());
        foo.setCreationDate(event.getCreationDate());
        foo.setEventStatus(event.getEventStatus());
        foo.setEventType(event.getEventType());

        AddressDTO addressDto = AddressMapper.entityToDTO(event.getAddress());
        foo.setAddress(addressDto);

        OrganizationDTO orgDto = OrganizationMapper.entityToDTO(event.getOrganization());
        foo.setOrganization(orgDto);

        return foo;
    }
}
