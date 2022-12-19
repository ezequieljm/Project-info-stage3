package com.info.groove.mapper;

import com.info.groove.dto.EventDTO;
import com.info.groove.dto.UniqueTurnDTO;
import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.Event;
import com.info.groove.entity.UniqueTurn;
import com.info.groove.entity.UserEntity;

public class UniqueTurnMapper {

    public static UniqueTurn dtoToEntity(UniqueTurnDTO uniqueTurnDTO){
        if (uniqueTurnDTO == null) return new UniqueTurn();

        UniqueTurn uniqueTurn = new UniqueTurn();
        uniqueTurn.setTurnId(uniqueTurnDTO.getTurnId());
        uniqueTurn.setTurnDate(uniqueTurnDTO.getTurnDate());
        uniqueTurn.setTurnStatus(uniqueTurnDTO.getTurnStatus());
        uniqueTurn.setKeyValue(uniqueTurnDTO.getKeyValue());

        Event event = EventMapper.dtoToEntity(uniqueTurnDTO.getEvent());
        uniqueTurn.setEvent(event);

        UserEntity user = UserEntityMapper.dtoToEntity(uniqueTurnDTO.getUser());
        uniqueTurn.setUser(user);

        return uniqueTurn;
    }

    public static UniqueTurnDTO entityToDto(UniqueTurn uniqueTurn) {
        if (uniqueTurn == null) return new UniqueTurnDTO();

        UniqueTurnDTO uniqueTurnDTO = new UniqueTurnDTO();

        uniqueTurnDTO.setTurnId(uniqueTurn.getTurnId());
        uniqueTurnDTO.setTurnDate(uniqueTurn.getTurnDate());
        uniqueTurnDTO.setTurnStatus(uniqueTurn.getTurnStatus());
        uniqueTurnDTO.setKeyValue(uniqueTurn.getKeyValue());

        EventDTO eventDto = EventMapper.entityToDto(uniqueTurn.getEvent());
        uniqueTurnDTO.setEvent(eventDto);

        UserEntityDTO userDto = UserEntityMapper.entityToDto(uniqueTurn.getUser());
        uniqueTurnDTO.setUser(userDto);

        return uniqueTurnDTO;
    }

}
