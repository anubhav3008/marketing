package com.anubhav.controller;

import com.anubhav.models.CommunicationAudit;
import com.anubhav.service.CommunicationAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analysis")
public class AnalyticsController {

    @Autowired
    private CommunicationAuditService communicationAuditService;

    @GetMapping("/mails")
    public List<CommunicationAudit> getMailAudits(){
        return this.communicationAuditService.getMailAudits();
    }
}
