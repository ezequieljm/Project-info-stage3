package com.info.groove.mapper;

import com.info.groove.dto.EventDTO;
import com.info.groove.entity.Event;

public class EventMapper {
    public static Event dtoToEntity(EventDTO eventDto) {
        if (eventDto == null) return new Event();

        Event event = new Event();

        event.setEventId(eventDto.getEventId());
        event.setEventName(eventDto.getEventName());
        event.setEventStatus(eventDto.getEventStatus());
        event.setAddress(eventDto.getAddress());
        event.setOrganization(eventDto.getOrganization());
        event.setCreationDate(eventDto.getCreationDate());
        event.setEventType(eventDto.getEventType());

        return event;
    }

    public static EventDTO entityToDto(Event event) {
        if (event == null) return new EventDTO();

        EventDTO foo = new EventDTO();

        foo.setEventId(event.getEventId());
        foo.setAddress(event.getAddress());
        foo.setEventName(event.getEventName());
        foo.setOrganization(event.getOrganization());
        foo.setCreationDate(event.getCreationDate());
        foo.setEventStatus(event.getEventStatus());
        foo.setEventType(event.getEventType());

        return foo;
    }
}
