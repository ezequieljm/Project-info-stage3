package com.info.groove.service.turns.recurrentturns;

import com.info.groove.dto.RecurrentEventTurnDTO;
import com.info.groove.entity.RecurrentEventTurn;
import com.info.groove.exceptions.*;

import java.util.List;

public interface IRecurrentEventTurnService {

    // Register a turn
    public RecurrentEventTurnDTO save(RecurrentEventTurnDTO recurrentEventTurnDTO)
            throws DuplicateDataError, UserNotFoundException, EventNotFoundException, OrganizationNotFoundException;

    // Update a turn
    public RecurrentEventTurnDTO updateRecurrentEventTurn(RecurrentEventTurnDTO recurrentEventTurnDTO, String key)
            throws UserNotFoundException, EventNotFoundException, OrganizationNotFoundException;

    // Delete a turn
    public void deleteRecurrentEventTurn(Long recTurnId) throws TurnNofFoundException;

    // Get all active turns by organization
    public List<RecurrentEventTurn> searchAllTurnsByOrganization(Long orgId, boolean active)
            throws OrganizationNotFoundException;

    // Get all turns with eventId and orgId
    public List<RecurrentEventTurn> searchAllTurnByOrgAndEvent(Long orgId, Long eventId)
            throws OrganizationNotFoundException, EventNotFoundException;
}
