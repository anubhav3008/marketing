package com.anubhav.controller;

import com.anubhav.models.MailAudit;
import com.anubhav.service.MailAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalyticsController {

    @Autowired
    private MailAuditService mailAuditService;

    @GetMapping("/mails")
    public List<MailAudit> getMailAudits(){
        return this.mailAuditService.getMailAudits();
    }
}
