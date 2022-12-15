package com.info.groove.service.turns.recurrentturns;

import com.info.groove.dto.RecurrentEventTurnDTO;
import com.info.groove.entity.Event;
import com.info.groove.entity.Organization;
import com.info.groove.entity.RecurrentEventTurn;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.*;
import com.info.groove.mapper.RecurrentEventTurnMapper;
import com.info.groove.repository.IEventRepository;
import com.info.groove.repository.IOrganizationRepository;
import com.info.groove.repository.IRecurrentEventTurnRepository;
import com.info.groove.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecurrentEventTurnService implements IRecurrentEventTurnService {

    @Autowired
    private IRecurrentEventTurnRepository recurrentEventTurnRepository;

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Override
    public RecurrentEventTurnDTO save(RecurrentEventTurnDTO recurrentEventTurnDTO)
            throws DuplicateDataError, UserNotFoundException, EventNotFoundException, OrganizationNotFoundException,
            OrganizationKeyNotEqual, DuplicateDateTimeException {

        // We verify if the turn not exists
        RecurrentEventTurn recurrentEventTurn = RecurrentEventTurnMapper.dtoToEntity(recurrentEventTurnDTO);

        Optional<RecurrentEventTurn> maybeTurn = recurrentEventTurnRepository.findById(recurrentEventTurn.getTurnId());

        if (!maybeTurn.isEmpty())
            throw new DuplicateDataError(String.format("The turn with id %s already exists", recurrentEventTurn.getTurnId()));

        // We control that event, user and organization exists
        Long eventId = recurrentEventTurn.getEventId().getEventId();
        Long userId = recurrentEventTurn.getUserId().getUserId();
        Long orgId = recurrentEventTurn.getEventId().getOrganization().getOrgId();

        Optional<Event> maybeEvent = eventRepository.findById(eventId);

        Optional<Organization> maybeOrganization = organizationRepository.findById(orgId);

        Optional<UserEntity> maybeUser = userEntityRepository.findById(userId);

        if (!maybeEvent.isEmpty())
            throw new EventNotFoundException(String.format("Event %s not exists", eventId));

        if(!maybeOrganization.isEmpty())
            throw new OrganizationNotFoundException(String.format("Organization %s not exists", orgId));

        if (!maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("User %s not exists", userId));

        Event originalEvent = maybeEvent.get();
        String eventOrgKey = originalEvent.getOrganization().getOrgKey();
        String turnOrgKey = recurrentEventTurn.getEventId().getOrganization().getOrgKey();

        if (!turnOrgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The key are not same");

        // We control the date and time of turn
        Date dateTime = recurrentEventTurn.getTurnDateTime();
        List<RecurrentEventTurn> turns = recurrentEventTurnRepository.findAll();

        for (RecurrentEventTurn turn: turns) {
            if (dateTime.equals(turn.getTurnDateTime()))
                throw new DuplicateDateTimeException("Already exists a turn with the date or time");
        }

        // Save the new turn
        RecurrentEventTurn anEntity = recurrentEventTurnRepository.save(recurrentEventTurn);

        return RecurrentEventTurnMapper.entityToDto(anEntity);
    }

    @Override
    public RecurrentEventTurnDTO updateRecurrentEventTurn(RecurrentEventTurnDTO recurrentEventTurnDTO, String key)
            throws UserNotFoundException, EventNotFoundException, OrganizationNotFoundException,
            TurnNofFoundException, DuplicateDateTimeException {
        // Convert dto to entity

        RecurrentEventTurn recurrentEventTurn = RecurrentEventTurnMapper.dtoToEntity(recurrentEventTurnDTO);

        // We must verify that the turn exists

        Long recTurnId = recurrentEventTurn.getTurnId();
        Optional<RecurrentEventTurn> maybeTurn = recurrentEventTurnRepository.findById(recTurnId);
        if (maybeTurn.isEmpty()) throw new TurnNofFoundException("Turn not exists");

        // We control the user, event and organization

        Long eventId = recurrentEventTurn.getEventId().getEventId();
        Long userId = recurrentEventTurn.getUserId().getUserId();
        Long orgId = recurrentEventTurn.getEventId().getOrganization().getOrgId();

        Optional<Event> maybeEvent = eventRepository.findById(eventId);

        Optional<Organization> maybeOrganization = organizationRepository.findById(orgId);

        Optional<UserEntity> maybeUser = userEntityRepository.findById(userId);

        if (!maybeEvent.isEmpty())
            throw new EventNotFoundException(String.format("Event %s not exists", eventId));

        if(!maybeOrganization.isEmpty())
            throw new OrganizationNotFoundException(String.format("Organization %s not exists", orgId));

        if (!maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("User %s not exists", userId));

        Event originalEvent = maybeEvent.get();
        String eventOrgKey = originalEvent.getOrganization().getOrgKey();
        String turnOrgKey = recurrentEventTurn.getEventId().getOrganization().getOrgKey();

        if (!turnOrgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The key are not same");

        String userKey = maybeUser.get().getUserKey();

        if (!userKey.equals(key)) throw new OrganizationKeyNotEqual("The key are not same");

        // We control the date and time of turn
        Date dateTime = recurrentEventTurn.getTurnDateTime();
        List<RecurrentEventTurn> turns = recurrentEventTurnRepository.findAll();

        for (RecurrentEventTurn turn: turns) {
            if (dateTime.equals(turn.getTurnDateTime()))
                throw new DuplicateDateTimeException("Already exists a turn with the date or time");
        }


        // We update turn

        RecurrentEventTurn anEntity = recurrentEventTurnRepository.save(recurrentEventTurn);

        return RecurrentEventTurnMapper.entityToDto(anEntity);

    }

    @Override
    public RecurrentEventTurnDTO deleteRecurrentEventTurn(Long recTurnId, String key) throws TurnNofFoundException {
        // We verify if turn exists

        // Long recTurnId = recurrentEventTurnDTO.getTurnId();
        Optional<RecurrentEventTurn> maybeTurn = recurrentEventTurnRepository.findById(recTurnId);
        if (maybeTurn.isEmpty()) throw new TurnNofFoundException("Turn not exists");

        // We delete the turn of logic form
        RecurrentEventTurn originalTurn = maybeTurn.get();
        originalTurn.setTurnStatus(0);
        RecurrentEventTurn newTurn = recurrentEventTurnRepository.save(originalTurn);
        return RecurrentEventTurnMapper.entityToDto(newTurn);
    }

    @Override
    public List<RecurrentEventTurn> searchAllTurnsByOrganization(Long orgId, boolean active)
            throws OrganizationNotFoundException {

        // We verify if organization exists
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        if (maybeOrg.isEmpty()) throw new OrganizationKeyNotEqual("The organization not exists");

        // we find all turns
        List<RecurrentEventTurn> turns = recurrentEventTurnRepository.findAll();
        List<RecurrentEventTurn> activeTurns = turns
                .stream()
                .filter(t -> t.getTurnStatus() == 1)
                .collect(Collectors.toList());

        return activeTurns;
    }

    @Override
    public List<RecurrentEventTurn> searchAllTurnByOrgAndEvent(Long orgId, Long eventId)
            throws OrganizationNotFoundException, EventNotFoundException {

        // We verify if organization and event exists
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        Optional<Event> maybeEvent = eventRepository.findById(eventId);

        if (maybeOrg.isEmpty()) throw new OrganizationKeyNotEqual("The organization not exists");

        if (maybeEvent.isEmpty()) throw new EventNotFoundException("The event not exists");

        return recurrentEventTurnRepository.findAll();
    }
}
