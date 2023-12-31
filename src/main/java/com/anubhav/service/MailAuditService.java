package com.anubhav.service;

import com.anubhav.models.MailAudit;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailAuditService {
    private List<MailAudit> mailAudits = new ArrayList<>();
    public void addAudit(MailAudit mailAudit){
        this.mailAudits.add(mailAudit);
    }

    public List<MailAudit> getMailAudits(){
        return this.mailAudits;
    }

}
