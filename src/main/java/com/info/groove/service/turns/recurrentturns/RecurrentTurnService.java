package com.info.groove.service.turns.recurrentturns;

import com.info.groove.dto.RecurrentTurnDTO;
import com.info.groove.entity.Event;
import com.info.groove.entity.Organization;
import com.info.groove.entity.RecurrentTurn;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.*;
import com.info.groove.mapper.RecurrentTurnMapper;
import com.info.groove.repository.IEventRepository;
import com.info.groove.repository.IOrganizationRepository;
import com.info.groove.repository.IRecurrentTurnRepository;
import com.info.groove.repository.IUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecurrentTurnService implements IRecurrentTurnService {

    @Autowired
    private IRecurrentTurnRepository recurrentTurnRepository;

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Override
    public RecurrentTurnDTO register(RecurrentTurnDTO recurrentTurnDTO)
            throws DuplicateDataError, UserNotFoundException, EventNotFoundException, OrganizationNotFoundException,
            OrganizationKeyNotEqual, DuplicateDateTimeException {

        // We verify if the turn not exists
        RecurrentTurn recurrentTurn = RecurrentTurnMapper.dtoToEntity(recurrentTurnDTO);

        Optional<RecurrentTurn> maybeTurn = recurrentTurnRepository.findById(recurrentTurn.getTurnId());

        if (!maybeTurn.isEmpty())
            throw new DuplicateDataError(String.format("The turn with id %s already exists", recurrentTurn.getTurnId()));

        // We control that event, user and organization exists
        Long eventId = recurrentTurn.getEvent().getEventId();
        Long userId = recurrentTurn.getUser().getUserId();
        Long orgId = recurrentTurn.getEvent().getOrganization().getOrgId();

        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        if (maybeEvent.isEmpty())
            throw new EventNotFoundException(String.format("Event %s not exists", eventId));

        Optional<Organization> maybeOrganization = organizationRepository.findById(orgId);
        if(maybeOrganization.isEmpty())
            throw new OrganizationNotFoundException(String.format("Organization %s not exists", orgId));

        Optional<UserEntity> maybeUser = userEntityRepository.findById(userId);
        if (maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("User %s not exists", userId));

        Event originalEvent = maybeEvent.get();
        String eventOrgKey = originalEvent.getOrganization().getOrgKey();
        String turnOrgKey = recurrentTurn.getEvent().getOrganization().getOrgKey();

        if (!turnOrgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The key are not same");

        // We control the date and time of turn
        Date dateTime = recurrentTurn.getTurnDateTime();
        List<RecurrentTurn> turns = recurrentTurnRepository.findAll();

        for (RecurrentTurn turn: turns) {
            if (dateTime.equals(turn.getTurnDateTime()))
                throw new DuplicateDateTimeException("Already exists a turn with the date or time");
        }

        // Save the new turn
        RecurrentTurn anEntity = recurrentTurnRepository.save(recurrentTurn);

        return RecurrentTurnMapper.entityToDto(anEntity);
    }

    @Override
    public RecurrentTurnDTO update(RecurrentTurnDTO recurrentTurnDTO)
            throws UserNotFoundException, EventNotFoundException, OrganizationNotFoundException,
            TurnNofFoundException, DuplicateDateTimeException {

        // Convert dto to entity
        RecurrentTurn recurrentTurn = RecurrentTurnMapper.dtoToEntity(recurrentTurnDTO);

        // We must verify that the turn exists
        Long recTurnId = recurrentTurn.getTurnId();
        Optional<RecurrentTurn> maybeTurn = recurrentTurnRepository.findById(recTurnId);
        if (maybeTurn.isEmpty()) throw new TurnNofFoundException("Turn not exists");

        // We control the user, event and organization
        Long eventId = recurrentTurn.getEvent().getEventId();
        Optional<Event> maybeEvent = eventRepository.findById(eventId);
        if (maybeEvent.isEmpty())
            throw new EventNotFoundException(String.format("Event %s not exists", eventId));

        Long userId = recurrentTurn.getUser().getUserId();
        Optional<UserEntity> maybeUser = userEntityRepository.findById(userId);
        if (maybeUser.isEmpty())
            throw new UserNotFoundException(String.format("User %s not exists", userId));

        Long orgId = recurrentTurn.getEvent().getOrganization().getOrgId();
        Optional<Organization> maybeOrganization = organizationRepository.findById(orgId);
        if(maybeOrganization.isEmpty())
            throw new OrganizationNotFoundException(String.format("Organization %s not exists", orgId));

        Event originalEvent = maybeEvent.get();
        String eventOrgKey = originalEvent.getOrganization().getOrgKey();
        String turnOrgKey = recurrentTurn.getEvent().getOrganization().getOrgKey();
        if (!turnOrgKey.equals(eventOrgKey)) throw new OrganizationKeyNotEqual("The key are not same");

        // We control the date and time of turn
        Date dateTime = recurrentTurn.getTurnDateTime();
        List<RecurrentTurn> turns = recurrentTurnRepository.findAll();
        for (RecurrentTurn turn: turns) {
            if (dateTime.equals(turn.getTurnDateTime()))
                throw new DuplicateDateTimeException("Already exists a turn with the date or time");
        }

        // We update turn
        RecurrentTurn anEntity = recurrentTurnRepository.save(recurrentTurn);
        return RecurrentTurnMapper.entityToDto(anEntity);

    }

    @Override
    public RecurrentTurnDTO logicalDeletion(RecurrentTurnDTO recurrentTurnDTO)
            throws TurnNofFoundException {
        // We control if the turn exists
        Optional<RecurrentTurn> maybeRecurrentTurn = recurrentTurnRepository.findById(recurrentTurnDTO.getTurnId());
        if (maybeRecurrentTurn.isEmpty())
            throw new TurnNofFoundException("The turn not exists");

        RecurrentTurn originalRecurrentTurn = maybeRecurrentTurn.get();

        originalRecurrentTurn.setTurnStatus(false);
        originalRecurrentTurn = recurrentTurnRepository.save(originalRecurrentTurn);
        return RecurrentTurnMapper.entityToDto(originalRecurrentTurn);
    }

    @Override
    public void delete(RecurrentTurnDTO recurrentTurnDTO) throws TurnNofFoundException {
        // We verify if turn exists

        Long recTurnId = recurrentTurnDTO.getTurnId();
        Optional<RecurrentTurn> maybeTurn = recurrentTurnRepository.findById(recTurnId);
        if (maybeTurn.isEmpty()) throw new TurnNofFoundException("Turn not exists");

        // We delete the turn of logic form
        RecurrentTurn originalTurn = maybeTurn.get();
        originalTurn.setTurnStatus(false);
        recurrentTurnRepository.deleteById(recTurnId);
    }

    @Override
    public List<RecurrentTurn> searchAllTurnsByOrganization(Long orgId)
            throws OrganizationNotFoundException {

        // We verify if organization exists
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        if (maybeOrg.isEmpty()) throw new OrganizationKeyNotEqual("The organization not exists");

        // we find all turns
        List<RecurrentTurn> turns = recurrentTurnRepository.findAll();
        List<RecurrentTurn> activeTurns = turns
                .stream()
                .filter(t -> t.getTurnStatus())
                .collect(Collectors.toList());

        return activeTurns;
    }

    @Override
    public List<RecurrentTurn> searchAllTurnByOrgAndEvent(Long orgId, Long eventId)
            throws OrganizationNotFoundException, EventNotFoundException {

        // We verify if organization and event exists
        Optional<Organization> maybeOrg = organizationRepository.findById(orgId);
        Optional<Event> maybeEvent = eventRepository.findById(eventId);

        if (maybeOrg.isEmpty()) throw new OrganizationKeyNotEqual("The organization not exists");

        if (maybeEvent.isEmpty()) throw new EventNotFoundException("The event not exists");

        List<RecurrentTurn> recurrentTurns = recurrentTurnRepository.findAll();
        List<RecurrentTurn> turnsByOrg = recurrentTurns
                .stream()
                .filter(t -> t.getEvent().getOrganization().getOrgId().equals(orgId))
                .collect(Collectors.toList());

        List<RecurrentTurn> turnsByEvent = turnsByOrg
                .stream()
                .filter(t -> t.getEvent().getEventId().equals(eventId))
                .collect(Collectors.toList());

        return turnsByEvent;

    }
}
