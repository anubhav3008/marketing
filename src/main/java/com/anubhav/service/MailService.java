package com.anubhav.service;

import com.anubhav.models.MailRequest;
import com.azure.communication.email.*;
import com.azure.communication.email.models.*;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    public boolean sendmail(MailRequest mailRequest){
        // You can get your connection string from your resource in the Azure portal.
        String connectionString = System.getenv("EmailConnectionString");
        EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

        EmailAddress toAddress = new EmailAddress("anubhav.workemail@gmail.com");

        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress("DoNotReply@a2463183-1387-4d24-ab7e-084c3752d1ea.azurecomm.net")
                .setToRecipients(toAddress)
                .setSubject(mailRequest.getTitle())
                .setBodyPlainText(mailRequest.getBody());

        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
        PollResponse<EmailSendResult> result = poller.waitForCompletion();
        return result.getStatus().isComplete();
    }
}
