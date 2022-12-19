package com.info.groove.service.turns.uniqueturns;


import com.info.groove.dto.UniqueTurnDTO;
import com.info.groove.entity.UniqueTurn;
import com.info.groove.exceptions.*;

import java.util.List;

public interface IUniqueTurnService {

    public UniqueTurnDTO register(UniqueTurnDTO uniqueTurnDTO)
            throws UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueTurnDTO update(UniqueTurnDTO uniqueTurnDTO)
            throws DuplicateDataError, UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
                OrganizationKeyNotEqual;

    public UniqueTurnDTO logicalDeletion(UniqueTurnDTO uniqueTurnDTO)
            throws TurnNofFoundException, UserNotFoundException;
    public void delete(UniqueTurnDTO uniqueTurnDTO)
            throws TurnNofFoundException, UserNotFoundException;

    public List<UniqueTurn> searchAllUniqueTurnsByOrg(Long orgId)
            throws OrganizationNotFoundException;

    public List<UniqueTurn> searchAllUniqueTurnsByOrgAndEvent(Long eventId, Long orgId)
            throws EventNotFoundException, OrganizationNotFoundException;
}
