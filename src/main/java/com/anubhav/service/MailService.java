package com.anubhav.service;

import com.anubhav.models.*;
import com.azure.communication.email.*;
import com.azure.communication.email.models.*;
import com.azure.core.util.polling.SyncPoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MailService {

    @Autowired
    UserService userService;

    @Autowired
    CommunicationAuditService communicationAuditService;

    @Autowired
    EventHubClient eventHubClient;

    public boolean sendmail(CommunicationRequest communicationRequest){
        String connectionString = System.getenv("EmailConnectionString");
        EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

        var users = this.userService.getUsers(communicationRequest.getUserRequest());

        users.forEach(user -> {
            EmailAddress toAddress = new EmailAddress(user.getEmail());
            EmailMessage emailMessage = new EmailMessage()
                    .setSenderAddress("DoNotReply@a2463183-1387-4d24-ab7e-084c3752d1ea.azurecomm.net")
                    .setToRecipients(toAddress)
                    .setSubject(communicationRequest.getTitle())
                    .setBodyPlainText(communicationRequest.getBody());
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
            poller.waitForCompletion();
        });

        communicationAuditService.addAudit(new CommunicationAudit(UUID.randomUUID(), communicationRequest.getTitle(),users.size(), CommunicationType.Email));
        EventRequest eventRequest = new EventRequest();
        eventRequest.setCount(users.size());
        eventRequest.setCommunicationType(CommunicationType.Email);
        eventRequest.setEventType(EventType.Sent);
        this.eventHubClient.send(eventRequest, 1);
        return true;
    }
}
