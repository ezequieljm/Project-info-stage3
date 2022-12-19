package com.info.groove.mapper;

import com.info.groove.dto.EventDTO;
import com.info.groove.dto.RecurrentTurnDTO;
import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.Event;
import com.info.groove.entity.RecurrentTurn;
import com.info.groove.entity.UserEntity;

public class RecurrentTurnMapper {

    public static RecurrentTurn dtoToEntity(RecurrentTurnDTO recurrentTurnDto) {
        if (recurrentTurnDto == null) return new RecurrentTurn();

        RecurrentTurn recurrentTurn = new RecurrentTurn();

        recurrentTurn.setTurnId(recurrentTurnDto.getTurnId());

        Event event = EventMapper.dtoToEntity(recurrentTurnDto.getEvent());
        recurrentTurn.setEvent(event);

        UserEntity user = UserEntityMapper.dtoToEntity(recurrentTurnDto.getUser());
        recurrentTurn.setUser(user);

        recurrentTurn.setTurnDateTime(recurrentTurnDto.getTurnDateTime());
        recurrentTurn.setTurnStatus(recurrentTurnDto.getTurnStatus());

        return recurrentTurn;
    }

    public static RecurrentTurnDTO entityToDto(RecurrentTurn recurrentTurn) {
        if (recurrentTurn == null) return new RecurrentTurnDTO();

        RecurrentTurnDTO foo = new RecurrentTurnDTO();

        foo.setTurnId(recurrentTurn.getTurnId());

        EventDTO eventDto = EventMapper.entityToDto(recurrentTurn.getEvent());
        foo.setEvent(eventDto);

        UserEntityDTO userEntityDTO = UserEntityMapper.entityToDto(recurrentTurn.getUser());
        foo.setUser(userEntityDTO);

        foo.setTurnDateTime(recurrentTurn.getTurnDateTime());
        foo.setTurnStatus(recurrentTurn.getTurnStatus());

        return foo;
    }

}
