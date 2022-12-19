package com.info.groove.mapper;

import com.info.groove.dto.UniqueTurnDTO;
import com.info.groove.entity.UniqueTurn;

public class UniqueTurnMapper {

    public static UniqueTurn dtoToEntity(UniqueTurnDTO uniqueTurnDTO){
        if (uniqueTurnDTO == null) return new UniqueTurn();

        UniqueTurn uniqueTurn = new UniqueTurn();

        uniqueTurn.setTurnId(uniqueTurnDTO.getTurnId());
        uniqueTurn.setEvent(uniqueTurnDTO.getEvent());
        uniqueTurn.setUser(uniqueTurnDTO.getUser());
        uniqueTurn.setTurnDate(uniqueTurnDTO.getTurnDate());
        uniqueTurn.setTurnStatus(uniqueTurnDTO.getTurnStatus());
        uniqueTurn.setKeyValue(uniqueTurnDTO.getKeyValue());

        return uniqueTurn;
    }

    public static UniqueTurnDTO entityToDto(UniqueTurn uniqueTurn) {
        if (uniqueTurn == null) return new UniqueTurnDTO();

        UniqueTurnDTO uniqueTurnDTO = new UniqueTurnDTO();

        uniqueTurnDTO.setTurnId(uniqueTurn.getTurnId());
        uniqueTurnDTO.setEvent(uniqueTurn.getEvent());
        uniqueTurnDTO.setUser(uniqueTurn.getUser());
        uniqueTurnDTO.setTurnDate(uniqueTurn.getTurnDate());
        uniqueTurnDTO.setTurnStatus(uniqueTurn.getTurnStatus());
        uniqueTurnDTO.setKeyValue(uniqueTurn.getKeyValue());

        return uniqueTurnDTO;
    }

}
