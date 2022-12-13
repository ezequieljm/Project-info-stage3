package com.info.groove.controllers;

import com.info.groove.service.turns.recurrentturns.IRecurrentEventTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RecurrentEventTurnController.BASIS)
public class RecurrentEventTurnController {
    public static final String BASIS = "/turns";

    @Autowired
    private IRecurrentEventTurnService recurrentEventTurnService;

    @GetMapping
    public String helloRecurrentEvents() {
        return "Hello recurrent events";
    }

}
