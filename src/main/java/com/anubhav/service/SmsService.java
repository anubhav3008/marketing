package com.anubhav.service;

import com.anubhav.models.CommunicationAudit;
import com.anubhav.models.CommunicationRequest;
import com.anubhav.models.CommunicationType;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SmsService {

    private VonageClient client;

    @Autowired
    private  UserService userService;

    @Autowired
    private CommunicationAuditService communicationAuditService;

    public SmsService(){
        String apiKey = System.getenv("SmsApiKey");
        String apiSecret = System.getenv("SmsApiSecret");
        this.client =  VonageClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();
    }

    public boolean send(CommunicationRequest communicationRequest){

        var users = this.userService.getUsers(communicationRequest.getUserRequest());
        int success = 0;

        for(var user : users){
            TextMessage message = new TextMessage("Anubhav Sales",
                    user.getPhone(),
                    communicationRequest.getBody()
            );

            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                System.out.println("Message sent successfully.");
                success++;
            } else {
                System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
            }
        }
        communicationAuditService.addAudit(new CommunicationAudit(UUID.randomUUID(), communicationRequest.getTitle(),success, CommunicationType.SMS));

        return true;

    }
}
