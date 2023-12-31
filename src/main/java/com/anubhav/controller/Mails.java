package com.anubhav.controller;

import com.anubhav.models.MailRequest;
import com.anubhav.service.MailService;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mails")
public class Mails {

    @Autowired
    private MailService mailService;

    @PostMapping("/")
    public boolean sendMail(@RequestBody MailRequest payload){
        return this.mailService.sendmail(payload);
    }
}
