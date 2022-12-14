package com.info.groove.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UniqueEventTurnController.BASIS)
public class UniqueEventTurnController {
    public static final String BASIS = "/unique";
}
