package com.anubhav.service;

import com.anubhav.models.CommunicationAudit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommunicationAuditService {
    private List<CommunicationAudit> communicationAudits = new ArrayList<>();
    public void addAudit(CommunicationAudit communicationAudit){
        this.communicationAudits.add(communicationAudit);
    }

    public List<CommunicationAudit> getMailAudits(){
        return this.communicationAudits;
    }

}
