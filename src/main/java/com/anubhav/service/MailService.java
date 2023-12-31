package com.anubhav.service;

import com.anubhav.models.MailAudit;
import com.anubhav.models.MailRequest;
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
    MailAuditService mailAuditService;

    public boolean sendmail(MailRequest mailRequest){
        // You can get your connection string from your resource in the Azure portal.
        // String connectionString = System.getenv("EmailConnectionString");
        String connectionString = "endpoint=https://communicationserviceanubhav.unitedstates.communication.azure.com/;accesskey=9hDPRH+MKc/hFjuINoJ3aV1USNGrl0ldjO1vmniO3Yqx0oFSYpPNxT4u5Mz4TjCisdUZ8uOdL9apDl0OLwFbSA==";
        EmailClient emailClient = new EmailClientBuilder().connectionString(connectionString).buildClient();

        var users = this.userService.getUsers(mailRequest.getUserRequest());

        users.forEach(user -> {
            EmailAddress toAddress = new EmailAddress(user.getEmail());
            EmailMessage emailMessage = new EmailMessage()
                    .setSenderAddress("DoNotReply@a2463183-1387-4d24-ab7e-084c3752d1ea.azurecomm.net")
                    .setToRecipients(toAddress)
                    .setSubject(mailRequest.getTitle())
                    .setBodyPlainText(mailRequest.getBody());
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(emailMessage, null);
            poller.waitForCompletion();
        });

        mailAuditService.addAudit(new MailAudit(UUID.randomUUID(), mailRequest.getTitle(),users.size()));

        return true;
    }
}
