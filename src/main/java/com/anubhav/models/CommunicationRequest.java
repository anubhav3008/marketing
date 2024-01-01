package com.anubhav.models;


public class CommunicationRequest {
    private String body;
    private String title;
    private CommunicationType communicationType;
    private UserRequest userRequest;

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
