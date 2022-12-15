package com.info.groove.mapper;

import com.info.groove.dto.RecurrentEventTurnDTO;
import com.info.groove.entity.RecurrentEventTurn;

public class RecurrentEventTurnMapper {

    public static RecurrentEventTurn dtoToEntity(RecurrentEventTurnDTO recurrentEventTurnDto) {
        if (recurrentEventTurnDto == null) return new RecurrentEventTurn();

        RecurrentEventTurn recurrentEventTurn = new RecurrentEventTurn();

        recurrentEventTurn.setTurnId(recurrentEventTurnDto.getTurnId());
        recurrentEventTurn.setEventId(recurrentEventTurnDto.getEvent());
        recurrentEventTurn.setUserId(recurrentEventTurnDto.getUser());
        recurrentEventTurn.setTurnDateTime(recurrentEventTurnDto.getTurnDateTime());
        recurrentEventTurn.setTurnStatus(recurrentEventTurnDto.getTurnStatus());

        return recurrentEventTurn;
    }

    public static RecurrentEventTurnDTO entityToDto(RecurrentEventTurn recurrentEventTurn) {
        if (recurrentEventTurn == null) return new RecurrentEventTurnDTO();

        RecurrentEventTurnDTO foo = new RecurrentEventTurnDTO();

        foo.setTurnId(recurrentEventTurn.getTurnId());
        foo.setEvent(recurrentEventTurn.getEventId());
        foo.setUser(recurrentEventTurn.getUserId());
        foo.setTurnDateTime(recurrentEventTurn.getTurnDateTime());
        foo.setTurnStatus(recurrentEventTurn.getTurnStatus());

        return foo;
    }

}
