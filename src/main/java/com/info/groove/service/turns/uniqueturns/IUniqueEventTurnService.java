package com.info.groove.service.turns.uniqueturns;


import com.info.groove.dto.UniqueEventTurnDTO;
import com.info.groove.entity.UniqueEventTurn;
import com.info.groove.exceptions.*;

import java.util.List;

public interface IUniqueEventTurnService {

    public UniqueEventTurnDTO registerUniqueTurn(UniqueEventTurnDTO uniqueEventTurnDTO, String orgKey)
            throws UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueEventTurnDTO updateUniqueTurn(UniqueEventTurnDTO uniqueEventTurnDTO, String orgKey)
            throws DuplicateDataError, UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueEventTurnDTO deleteUniqueTurn(Long turnId, String orgKey)
            throws TurnNofFoundException, OrganizationKeyNotEqual;

    public List<UniqueEventTurn> searchAllUniqueTurnByOrg(String orgKey) throws OrganizationNotFoundException;

    public List<UniqueEventTurn> searchAllUniqueTurnsByOrgAndEvent(Long eventId, String orgKey)
            throws EventNotFoundException, OrganizationNotFoundException;
}
