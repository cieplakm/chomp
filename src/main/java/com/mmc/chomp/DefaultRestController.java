package com.mmc.chomp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mmc.chomp.communication.ResponserProcessor;
import com.mmc.chomp.communication.request.CreateGameRequest;
import com.mmc.chomp.communication.request.JoinGameRequest;
import com.mmc.chomp.communication.request.MoveRequest;
import com.mmc.chomp.communication.request.RatingRequest;
import com.mmc.chomp.communication.request.StartRequest;
import com.mmc.chomp.communication.request.StateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chomp")
public class DefaultRestController {

    private ResponserProcessor responserProcessor;

    @Autowired
    public DefaultRestController(ResponserProcessor responserProcessor) {
        this.responserProcessor = responserProcessor;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createGame(@RequestBody CreateGameRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinGame(@RequestBody JoinGameRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String start(@RequestBody StartRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public String move(@RequestBody MoveRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public String move(@RequestBody StateRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.POST)
    public String move(@RequestBody RatingRequest request) throws JsonProcessingException {
        return responserProcessor.response(request);
    }




}
