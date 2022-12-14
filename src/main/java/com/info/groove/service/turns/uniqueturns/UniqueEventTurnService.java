package com.info.groove.service.turns.uniqueturns;

import com.info.groove.repository.IUniqueEventTurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueEventTurnService implements IUniqueEventTurnService {

    @Autowired
    private IUniqueEventTurnRepository uniqueEventTurnRepository;
}
