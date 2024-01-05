package com.anubhav.controller;


import com.anubhav.models.SimulationRequest;
import com.anubhav.service.EventHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulate")
public class SimulationController {

    @Autowired
    EventHubClient eventHubClient;

    @PostMapping("/")
    public Boolean simulateClick(@RequestBody SimulationRequest simulationRequest, @RequestParam int count){
        this.eventHubClient.send(simulationRequest, count);
        return true;
    }
}
