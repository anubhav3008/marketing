package com.anubhav.controller;

import com.anubhav.models.CommunicationRequest;
import com.anubhav.models.CommunicationType;
import com.anubhav.service.MailService;
import com.anubhav.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communicate")
public class Mails {

    @Autowired
    private MailService mailService;

    @Autowired
    private SmsService smsService;

    @PostMapping("/")
    public boolean sendMail(@RequestBody CommunicationRequest payload){
        if(payload.getCommunicationType() == CommunicationType.Email){
            return this.mailService.sendmail(payload);
        }

        return this.smsService.send(payload);
    }
}
