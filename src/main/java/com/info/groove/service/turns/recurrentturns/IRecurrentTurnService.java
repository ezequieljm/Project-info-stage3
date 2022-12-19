package com.info.groove.service.turns.recurrentturns;

import com.info.groove.dto.RecurrentTurnDTO;
import com.info.groove.entity.RecurrentTurn;
import com.info.groove.exceptions.*;

import java.util.List;

public interface IRecurrentTurnService {

    // Register a turn
    public RecurrentTurnDTO register(RecurrentTurnDTO recurrentTurnDTO)
            throws DuplicateDataError, UserNotFoundException, EventNotFoundException, OrganizationNotFoundException;

    // Update a turn
    public RecurrentTurnDTO update(RecurrentTurnDTO recurrentTurnDTO)
            throws UserNotFoundException, EventNotFoundException, OrganizationNotFoundException;

    // Delete a turn
    public RecurrentTurnDTO logicalDeletion(RecurrentTurnDTO recurrentTurnDTO)
            throws TurnNofFoundException;

    public void delete(RecurrentTurnDTO recurrentTurnDTO)
        throws TurnNofFoundException;

    // Get all active turns by organization
    public List<RecurrentTurn> searchAllTurnsByOrganization(Long orgId) throws OrganizationNotFoundException;

    // Get all turns with eventId and orgId
    public List<RecurrentTurn> searchAllTurnByOrgAndEvent(Long orgId, Long eventId)
            throws OrganizationNotFoundException, EventNotFoundException;
}
