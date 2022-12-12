package com.info.groove.mapper;

import com.info.groove.dto.RecurrentEventTurnDTO;
import com.info.groove.entity.RecurrentEventTurn;

public class RecurrentEventTurnMapper {

    public static RecurrentEventTurn dtoToEntity(RecurrentEventTurnDTO recurrentEventTurnDto) {
        if (recurrentEventTurnDto == null) return new RecurrentEventTurn();

        RecurrentEventTurn recurrentEventTurn = new RecurrentEventTurn();

        recurrentEventTurn.setTurnId(recurrentEventTurnDto.getTurnId());
        recurrentEventTurn.setEventId(recurrentEventTurnDto.getEventId());
        recurrentEventTurn.setUserId(recurrentEventTurnDto.getUserId());
        recurrentEventTurn.setTurnDateTime(recurrentEventTurnDto.getTurnDateTime());
        recurrentEventTurn.setTurnStatus(recurrentEventTurnDto.getTurnStatus());

        return recurrentEventTurn;
    }

    public static RecurrentEventTurnDTO entityToDto(RecurrentEventTurn recurrentEventTurn) {
        if (recurrentEventTurn == null) return new RecurrentEventTurnDTO();

        RecurrentEventTurnDTO foo = new RecurrentEventTurnDTO();

        foo.setTurnId(recurrentEventTurn.getTurnId());
        foo.setEventId(recurrentEventTurn.getEventId());
        foo.setUserId(recurrentEventTurn.getUserId());
        foo.setTurnDateTime(recurrentEventTurn.getTurnDateTime());
        foo.setTurnStatus(recurrentEventTurn.getTurnStatus());

        return foo;
    }

}
