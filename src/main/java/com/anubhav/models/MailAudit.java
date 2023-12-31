package com.anubhav.models;

import java.util.UUID;

public class MailAudit {
    UUID id;
    String title;
    Integer sentCount;

    public MailAudit(UUID id, String title, Integer sentCount) {
        this.id = id;
        this.title = title;
        this.sentCount = sentCount;
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
}
