package com.anubhav.controller;


import com.anubhav.models.EventRequest;
import com.anubhav.service.EventHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulate")
public class SimulationController {

    @Autowired
    EventHubClient eventHubClient;

    @PostMapping("/")
    public Boolean collectEvent(@RequestBody EventRequest eventRequest, @RequestParam int count){
        this.eventHubClient.send(eventRequest, count);
        return true;
    }
}
