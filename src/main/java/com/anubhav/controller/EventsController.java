package com.anubhav.controller;


import com.anubhav.models.CommunicationType;
import com.anubhav.models.EventRequest;
import com.anubhav.models.EventType;
import com.anubhav.service.EventHubClient;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/event")
public class EventsController {

    @Autowired
    EventHubClient eventHubClient;

    @PostMapping("/simulate")
    public Boolean simulateEvent(@RequestBody EventRequest eventRequest, @RequestParam int count){
        this.eventHubClient.send(eventRequest, count);
        return true;
    }

    @GetMapping("/")
    public void collectEvent(@RequestParam CommunicationType communicationType,
                                @RequestParam EventType eventType,
                                @RequestParam String url,
                                HttpServletResponse response) throws IOException {
        EventRequest eventRequest = new EventRequest();
        eventRequest.setEventType(eventType);
        eventRequest.setCommunicationType(communicationType);
        eventRequest.setCount(1);
        this.eventHubClient.send(eventRequest, 1);
        response.sendRedirect(url);
    }
}
