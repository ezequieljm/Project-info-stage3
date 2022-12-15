package com.info.groove.service.turns.uniqueturns;

import com.info.groove.dto.UniqueEventTurnDTO;
import com.info.groove.entity.Organization;
import com.info.groove.entity.UniqueEventTurn;
import com.info.groove.exceptions.*;
import com.info.groove.mapper.UniqueEventTurnMapper;
import com.info.groove.repository.IEventRepository;
import com.info.groove.repository.IOrganizationRepository;
import com.info.groove.repository.IUniqueEventTurnRepository;
import com.info.groove.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniqueEventTurnService implements IUniqueEventTurnService {

    @Autowired
    private IUniqueEventTurnRepository uniqueEventTurnRepository;

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Override
    public UniqueEventTurnDTO registerUniqueTurn(UniqueEventTurnDTO uniqueEventTurnDTO, String orgKey)
            throws UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
            OrganizationKeyNotEqual {

        // We convert the dto to entity
        UniqueEventTurn turn = UniqueEventTurnMapper.dtoToEntity(uniqueEventTurnDTO);

        // We verify if user, organization and event exists
        Long userId = turn.getUser().getUserId();
        Long orgId = turn.getEvent().getOrganization().getOrgId();
        String key = turn.getEvent().getOrganization().getOrgKey();
        Long eventId = turn.getEvent().getEventId();

        userEntityRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("The user not exists"));

        organizationRepository
                .findById(orgId)
                .orElseThrow(() -> new OrganizationNotFoundException("The organization not exists"));

        eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("The event not exists"));

        // We control the organization keys

        if (!key.equals(orgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        // we register the new turn
        UniqueEventTurn storedTurn = uniqueEventTurnRepository.save(turn);

        return UniqueEventTurnMapper.entityToDto(storedTurn);

    }

    @Override
    public UniqueEventTurnDTO updateUniqueTurn(UniqueEventTurnDTO uniqueEventTurnDTO, String orgKey)
            throws UserNotFoundException, OrganizationNotFoundException, EventNotFoundException,
            OrganizationKeyNotEqual, TurnNofFoundException {
        // We convert the dto to entity
        UniqueEventTurn turn = UniqueEventTurnMapper.dtoToEntity(uniqueEventTurnDTO);

        // We verify if user, organization and event exists
        Long turnId = turn.getTurnId();
        Long userId = turn.getUser().getUserId();
        Long orgId = turn.getEvent().getOrganization().getOrgId();
        String key = turn.getEvent().getOrganization().getOrgKey();
        Long eventId = turn.getEvent().getEventId();

        uniqueEventTurnRepository
                .findById(turnId)
                .orElseThrow(
                        () -> new TurnNofFoundException(
                                "The turn not exists. You cannot update a turn that isn't registered"
                        )
                );

        userEntityRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("The user not exists"));

        organizationRepository
                .findById(orgId)
                .orElseThrow(() -> new OrganizationNotFoundException("The organization not exists"));

        eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("The event not exists"));

        // We control the organization keys

        if (!key.equals(orgKey)) throw new OrganizationKeyNotEqual("The keys are not same");

        // we register the new turn
        UniqueEventTurn storedTurn = uniqueEventTurnRepository.save(turn);

        return UniqueEventTurnMapper.entityToDto(storedTurn);
    }

    @Override
    public UniqueEventTurnDTO deleteUniqueTurn(Long turnId, String orgKey) throws TurnNofFoundException, OrganizationKeyNotEqual {
        // We control that the turn and organization are already regitered.
        // We search the turn and organization
        Optional<UniqueEventTurn> maybeTurn = uniqueEventTurnRepository.findById(turnId);
        if (!maybeTurn.isEmpty())throw new TurnNofFoundException("Turn cannot be delete because it does not exists");

//        uniqueEventTurnRepository
//                .findById(turnId)
//                .orElseThrow(() -> new TurnNofFoundException("Turn cannot be delete because it does not exists"));

        UniqueEventTurn originalTurn = maybeTurn.get();
        String key = originalTurn.getEvent().getOrganization().getOrgKey();
        if (!key.equals(orgKey)) throw new OrganizationKeyNotEqual("The keys are not same");


        // If the turn exists we delete it
        originalTurn.setTurnStatus(0);
        UniqueEventTurn aTurn = uniqueEventTurnRepository.save(originalTurn);
        return UniqueEventTurnMapper.entityToDto(aTurn);

    }

    @Override
    public List<UniqueEventTurn> searchAllUniqueTurnByOrg(String orgKey) throws OrganizationNotFoundException {
        // We verify if organization exists
        Organization org = organizationRepository.findByOrgKey(orgKey);
        if (org == null) throw new OrganizationNotFoundException("the organization not exists");

        // We get all turns
        List<UniqueEventTurn> turns = uniqueEventTurnRepository.findAll();

        // Filter by active status and organization key
        List<UniqueEventTurn> activeTurns = turns
                .stream()
                .filter(t -> t.getTurnStatus() == 1)
                .collect(Collectors.toList());

        return activeTurns
                .stream()
                .filter(
                        t -> t.getEvent()
                            .getOrganization()
                            .getOrgKey()
                            .equals(orgKey)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<UniqueEventTurn> searchAllUniqueTurnsByOrgAndEvent(Long eventId, String orgKey)
            throws EventNotFoundException, OrganizationNotFoundException {

        // We verify if event and organization exists
        Organization org = organizationRepository.findByOrgKey(orgKey);
        if (org == null) throw new OrganizationNotFoundException("the organization not exists");

        eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("The event not exists"));

        // We get all turns
        List<UniqueEventTurn> turns = uniqueEventTurnRepository.findAll();

        // Filter by active status and organization key
        List<UniqueEventTurn> activeTurns = turns
                .stream()
                .filter(t -> t.getTurnStatus() == 1)
                .collect(Collectors.toList());

        List<UniqueEventTurn> turnsWithOrgAndEvent = new ArrayList<UniqueEventTurn>();

        for (UniqueEventTurn turn: activeTurns) {
            Long turnEventId = turn.getEvent().getEventId();
            String turnOrgKey = turn.getEvent().getOrganization().getOrgKey();
            if (turnEventId.equals(eventId) && turnOrgKey.equals(orgKey)) turnsWithOrgAndEvent.add(turn);
        }

        return turnsWithOrgAndEvent;
    }
}
