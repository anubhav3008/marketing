package com.anubhav.service;

import com.anubhav.models.EventRequest;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventData;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EventHubClient {

    // Create an EventHubClientBuilder and set the connection string and event hub name
    EventHubClientBuilder  eventHubClientBuilder;
    Gson gson;

    public EventHubClient(){
        String connectionString = System.getenv("eventHubConnectionString");
        String eventHubName = System.getenv("eventHubName");
        this.eventHubClientBuilder = new EventHubClientBuilder()
                .connectionString(connectionString, eventHubName);

        this.gson = new Gson();

    }
    public void send(EventRequest eventRequest, int count){
        try (var producerClient = eventHubClientBuilder.buildProducerClient()) {
            // Create a batch
            for(int i =0;i<count;i++){
                EventData eventData = new EventData(this.gson.toJson(eventRequest));
                // Send the batch to the event hub
                producerClient.send(Collections.singleton(eventData));
                System.out.println("Message sent successfully!");
            }
        } catch (Exception e) {
            System.err.println("Error publishing message: " + e.getMessage());
        }
    }
}
