package com.info.groove.service.turns.uniqueturns;


import com.info.groove.dto.UniqueTurnDTO;
import com.info.groove.entity.UniqueTurn;
import com.info.groove.exceptions.*;

import java.util.List;

public interface IUniqueEventTurnService {

    public UniqueTurnDTO registerUniqueTurn(UniqueTurnDTO uniqueTurnDTO, String orgKey)
            throws UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueTurnDTO updateUniqueTurn(UniqueTurnDTO uniqueTurnDTO, String orgKey)
            throws DuplicateDataError, UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueTurnDTO deleteUniqueTurn(Long turnId, String orgKey)
            throws TurnNofFoundException, OrganizationKeyNotEqual;

    public List<UniqueTurn> searchAllUniqueTurnByOrg(String orgKey) throws OrganizationNotFoundException;

    public List<UniqueTurn> searchAllUniqueTurnsByOrgAndEvent(Long eventId, String orgKey)
            throws EventNotFoundException, OrganizationNotFoundException;
}
