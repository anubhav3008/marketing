package com.anubhav.models;

import java.util.UUID;

public class CommunicationAudit {
    UUID id;
    String title;
    Integer sentCount;
    CommunicationType communicationType;

    public CommunicationAudit(UUID id, String title, Integer sentCount, CommunicationType communicationType) {
        this.id = id;
        this.title = title;
        this.sentCount = sentCount;
        this.communicationType = communicationType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }
}
