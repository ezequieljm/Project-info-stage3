package com.info.groove.mapper;

import com.info.groove.dto.UniqueEventTurnDTO;
import com.info.groove.entity.UniqueEventTurn;

public class UniqueEventTurnMapper {

    public static UniqueEventTurn dtoToEntity(UniqueEventTurnDTO uniqueEventTurnDTO){
        if (uniqueEventTurnDTO == null) return new UniqueEventTurn();

        UniqueEventTurn uniqueEventTurn = new UniqueEventTurn();

        uniqueEventTurn.setTurnId(uniqueEventTurnDTO.getTurnId());
        uniqueEventTurn.setEventId(uniqueEventTurnDTO.getEventId());
        uniqueEventTurn.setUserId(uniqueEventTurnDTO.getUserId());
        uniqueEventTurn.setTurnDate(uniqueEventTurnDTO.getTurnDate());
        uniqueEventTurn.setTurnStatus(uniqueEventTurnDTO.getTurnStatus());
        uniqueEventTurn.setKeyValue(uniqueEventTurnDTO.getKeyValue());

        return uniqueEventTurn;
    }

    public static UniqueEventTurnDTO entityToDto(UniqueEventTurn uniqueEventTurn) {
        if (uniqueEventTurn == null) return new UniqueEventTurnDTO();

        UniqueEventTurnDTO uniqueEventTurnDTO = new UniqueEventTurnDTO();

        uniqueEventTurnDTO.setTurnId(uniqueEventTurn.getTurnId());
        uniqueEventTurnDTO.setEventId(uniqueEventTurn.getEventId());
        uniqueEventTurnDTO.setUserId(uniqueEventTurn.getUserId());
        uniqueEventTurnDTO.setTurnDate(uniqueEventTurn.getTurnDate());
        uniqueEventTurnDTO.setTurnStatus(uniqueEventTurn.getTurnStatus());
        uniqueEventTurnDTO.setKeyValue(uniqueEventTurn.getKeyValue());

        return uniqueEventTurnDTO;
    }

}
